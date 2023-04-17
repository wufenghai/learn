package com.example.canaldemo.Sync;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.example.canaldemo.util.CanalUtil;
import com.google.protobuf.ByteString;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * canal客户端
 *
 * @author wfh
 * @create 2023/4/13 9:46
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SyncInstanceToEs {

    private final CanalUtil canalUtil;

    private final RestHighLevelClient restHighLevelClient;

    /**
     * 监听canal数据
     */
    public void run() throws Exception {
        // 获取canal服务的连接
        CanalConnector connector = canalUtil.getConnector();
        try {
            //连接
            connector.connect();
            // 订阅数据库，监控数据库 testdb库的t_为前缀的表
            connector.subscribe("testdb.t_.*");
            // 尝试读取服务端是否有新数据
            while (true) {

                // 获取数据，每次获取100条
                Message message = connector.get(100);
                //获取批量ID
                long batchId = message.getId();
                // 获取 Entry 集合
                List<CanalEntry.Entry> entryList = message.getEntries();
                // 判断集合是否为空，如果为空，则等待继续拉取
                if (CollectionUtils.isEmpty(entryList)) {
                    //没有数据，休息一会
                    TimeUnit.SECONDS.sleep(2);
                } else {
                    // 遍历 entries 单条解析
                    for (CanalEntry.Entry entry : entryList) {
                        // 获取表名
                        String tableName = entry.getHeader().getTableName();
                        //获取类型
                        CanalEntry.EntryType entryType = entry.getEntryType();
                        // 判断当前entryType类型是是否为 RowData 类型
                        if (CanalEntry.EntryType.ROWDATA.equals(entryType)) {
                            //获取序列化后的数据
                            ByteString storeValue = entry.getStoreValue();
                            //反序列化数据
                            CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(storeValue);
                            //获取当前事件操作类型
                            CanalEntry.EventType eventType = rowChange.getEventType();
                            //获取数据集
                            List<CanalEntry.RowData> rowDataList = rowChange.getRowDatasList();
                            if (eventType == CanalEntry.EventType.INSERT) {
                                log.info("------新增操作------");
                                insert(rowDataList);

                            } else if (eventType == CanalEntry.EventType.UPDATE) {
                                log.info("------更新操作------");
                                update(rowDataList);

                            } else if (eventType == CanalEntry.EventType.DELETE) {
                                log.info("------删除操作------");
                                delete(rowDataList);
                            }
                        }
                    }
                }
                // 提交确认
//                connector.ack(batchId);
            }

        } finally {
            //报错断开连接
            connector.disconnect();
            //没有数据，休息一会
            TimeUnit.SECONDS.sleep(2);
            //重新创建连接
            run();
        }
    }


    /**
     * 增量数据插入es
     *
     * @param rowDataList
     * @throws IOException
     */
    private void insert(List<CanalEntry.RowData> rowDataList) throws IOException {
        for (CanalEntry.RowData rowData : rowDataList) {
            List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
            Map<String, String> map = getFormIdAndKey(afterColumnsList);
            // TODO: 2023/4/13  后面若需要的话在规范我们的mapping
//            //索引客户端
//            IndicesClient indices = restHighLevelClient.indices();
//            boolean exists = indices.exists(new GetIndexRequest(formId), RequestOptions.DEFAULT);
//            //若不存在的话  就去创建当前表单id的index
//            if (!exists) {
//                CreateIndexResponse createIndexResponse = indices.create(new CreateIndexRequest(formId), RequestOptions.DEFAULT);
//                boolean isAcknowledged = createIndexResponse.isAcknowledged();
//                if (!isAcknowledged) {
//                    //若创建失败则记录日志
//                    log.info("afterColumnsList:" + afterColumnsList);
//                }
//
//            }
            //创建以formid为index的key为id的文档
            IndexRequest indexRequest = new IndexRequest(map.get("formId")).id(map.get("key"));
            log.info("insert操作:formId：" + map.get("formId") + "   key：" + map.get("key"));
            //创建字段数据
            Map sourceMap = new HashMap<>();
            for (CanalEntry.Column column : afterColumnsList) {
                sourceMap.put(column.getName(), column.getValue());
            }
            log.info("sourceMap：" + sourceMap);
            indexRequest.source(sourceMap);
            //新增数据
            restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        }

    }

    /**
     * 增量数据修改es
     *
     * @param rowDataList
     * @throws IOException
     */
    private void update(List<CanalEntry.RowData> rowDataList) throws IOException {
        for (CanalEntry.RowData rowData : rowDataList) {
            List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
            Map<String, String> map = getFormIdAndKey(afterColumnsList);
            //修改以formid为index的key为id的文档
            UpdateRequest updateRequest = new UpdateRequest().index(map.get("formId")).id(map.get("key"));
            log.info("update操作:formId：" + map.get("formId") + "   key：" + map.get("key"));
            //创建字段数据
            Map sourceMap = new HashMap<>();
            for (CanalEntry.Column column : afterColumnsList) {
                //插入有修改的数据
                if (column.getUpdated()) {
                    sourceMap.put(column.getName(), column.getValue());
                }
            }
            log.info("sourceMap：" + sourceMap);
            updateRequest.doc(sourceMap);
            restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        }
    }

    /**
     * 删除以formid为index的key为id的文档
     *
     * @param rowDataList
     * @throws IOException
     */
    private void delete(List<CanalEntry.RowData> rowDataList) throws IOException {
        for (CanalEntry.RowData rowData : rowDataList) {
            List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
            Map<String, String> map = getFormIdAndKey(beforeColumnsList);
            //删除以formid为index的key为id的文档
            DeleteRequest deleteRequest = new DeleteRequest().index(map.get("formId")).id(map.get("key"));
            log.info("delete操作:formId：" + map.get("formId") + "   key：" + map.get("key"));
            restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        }
    }

    /**
     * 获取对应的 formid和key
     *
     * @param columnsList
     * @return
     */
    private Map getFormIdAndKey(List<CanalEntry.Column> columnsList) {
        Map map = new HashMap();
        for (CanalEntry.Column column : columnsList) {
            if ("form_id".equals(column.getName())) {
                //es只接受小写字母
                map.put("formId", column.getValue().toLowerCase());
            }
            //若为主键的话
            if (column.getIsKey()) {
                map.put("key", column.getValue());
            }
        }
        return map;
    }
}
