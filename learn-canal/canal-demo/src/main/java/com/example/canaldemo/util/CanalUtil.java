package com.example.canaldemo.util;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.example.canaldemo.model.CanalEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * canal工具
 * @author wfh
 * @create 2023/4/12 17:24
 */
@Component
@RequiredArgsConstructor
public class CanalUtil {

    private final CanalEntity canalEntity;


    /**
     * 获取canal 连接
     *
     * @return
     */
    public CanalConnector getConnector() {
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(
                canalEntity.getHost(), canalEntity.getPort()), canalEntity.getDestination(), canalEntity.getUsername(), canalEntity.getPassword());
        return connector;
    }


}