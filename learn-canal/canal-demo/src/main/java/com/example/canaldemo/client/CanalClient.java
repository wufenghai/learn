package com.example.canaldemo.client;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.common.utils.AddressUtils;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.example.canaldemo.model.Music;
import com.google.protobuf.ByteString;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.client.ElasticsearchClient;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wfh
 * @create 2023/4/12 15:54
 */
@Slf4j
@Component
public class CanalClient {
//
//    @Resource
//    private ElasticsearchClient client;


    /**
     * 实时数据同步程序
     *
     * @throws InterruptedException
     */
    public void run() throws InterruptedException, IOException {
        // 获取canal服务的连接
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(
                "localhost", 11111), "example", "", "");
        try {
            //连接
            connector.connect();
            // 订阅数据库，监控数据库 trcanal所有表
            connector.subscribe("testdb.t_.*");
            //处理失败, 回滚数据，后续重新获取数据
            connector.rollback();
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
                    continue;
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

                                List<Music> musicList = new ArrayList<>();
                                for (CanalEntry.RowData rowData : rowDataList) {
                                    List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
                                    for (CanalEntry.Column column : afterColumnsList) {
                                        String name = column.getName();
                                        String value = column.getValue();
                                        boolean isKey = column.getIsKey();
                                        int sqlType = column.getSqlType();
                                        String mysqlType = column.getMysqlType();
                                        boolean isNull = column.getIsNull();
                                        System.out.println("字段名：" + name + "；字段值：" + value + "；是否为主键：" + isKey);
                                        System.out.println("sqlType：" + sqlType + "；mysqlType：" + mysqlType + "；isNull：" + isNull);
                                        System.out.println("----------------------------------------------------------------");
                                    }
//                                    musicList.add(createMusic(rowData.getAfterColumnsList()));
                                }
                                //es批量新增文档
//                            index(musicList);
                                //打印新增集合
                                log.info(Arrays.toString(musicList.toArray()));
                            } else if (eventType == CanalEntry.EventType.UPDATE) {
                                log.info("------更新操作------");

                                List<Music> beforeMusicList = new ArrayList<>();
                                List<Music> afterMusicList = new ArrayList<>();
                                for (CanalEntry.RowData rowData : rowDataList) {
                                    //更新前
                                    beforeMusicList.add(createMusic(rowData.getBeforeColumnsList()));
                                    //更新后
                                    afterMusicList.add(createMusic(rowData.getAfterColumnsList()));
                                }
                                //es批量更新文档
//                            index(afterMusicList);
                                //打印更新前集合
                                log.info("更新前：{}", Arrays.toString(beforeMusicList.toArray()));
                                //打印更新后集合
                                log.info("更新后：{}", Arrays.toString(afterMusicList.toArray()));
                            } else if (eventType == CanalEntry.EventType.DELETE) {
                                //删除操作
                                log.info("------删除操作------");

                                List<String> idList = new ArrayList<>();
                                for (CanalEntry.RowData rowData : rowDataList) {
                                    for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
                                        if ("id".equals(column.getName())) {
                                            idList.add(column.getValue());
                                            break;
                                        }
                                    }
                                }
                                //es批量删除文档
//                            delete(idList);
                                //打印删除id集合
                                log.info(Arrays.toString(idList.toArray()));
                            }
                        }

                    }
                }
                connector.ack(batchId); // 提交确认
            }
        } finally {
            //
            connector.disconnect();
        }
    }

    /**
     * 根据canal获取的数据创建Music对象
     *
     * @param columnList
     * @return
     */
    private Music createMusic(List<CanalEntry.Column> columnList) {
        Music music = new Music();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (CanalEntry.Column column : columnList) {
            switch (column.getName()) {
//                case "id" -> music.setId(column.getValue());
//                case "name" -> music.setName(column.getValue());
//                case "singer" -> music.setSinger(column.getValue());
//                case "image_url" -> music.setImageUrl(column.getValue());
//                case "music_url" -> music.setMusicUrl(column.getValue());
//                case "lrc_url" -> music.setLrcUrl(column.getValue());
//                case "type_id" -> music.setTypeId(column.getValue());
//                case "is_deleted" -> music.setIsDeleted(Integer.valueOf(column.getValue()));
//                case "create_time" ->
//                        music.setCreateTime(Date.from(LocalDateTime.parse(column.getValue(), formatter).atZone(ZoneId.systemDefault()).toInstant()));
//                case "update_time" ->
//                        music.setUpdateTime(Date.from(LocalDateTime.parse(column.getValue(), formatter).atZone(ZoneId.systemDefault()).toInstant()));
//                default -> {
//                }
            }
        }

        return music;
    }

    /**
     * es批量新增、更新文档（不存在：新增， 存在：更新）
     *
     * @param musicList 音乐集合
     * @throws IOException
     */
//    private void index(List<Music> musicList) throws IOException {
//        BulkRequest.Builder br = new BulkRequest.Builder();
//
//        musicList.forEach(music -> br
//                .operations(op -> op
//                        .index(idx -> idx
//                                .index("music")
//                                .id(music.getId())
//                                .document(music))));
//
//        client.bulk(br.build());
//    }

    /**
     * es批量删除文档
     *
     * @param idList 音乐id集合
     * @throws IOException
     */
//    private void delete(List<String> idList) throws IOException {
//        BulkRequest.Builder br = new BulkRequest.Builder();
//
//        idList.forEach(id -> br
//                .operations(op -> op
//                        .delete(idx -> idx
//                                .index("music")
//                                .id(id))));
//
//        client.bulk(br.build());
//    }


}