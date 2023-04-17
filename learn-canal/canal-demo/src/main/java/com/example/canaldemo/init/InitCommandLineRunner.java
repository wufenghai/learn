package com.example.canaldemo.init;

import com.example.canaldemo.Sync.SyncInstanceToEs;
import com.example.canaldemo.model.CanalEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 开机初始化
 *
 * @author wfh
 * @create 2023/4/17 11:37
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class InitCommandLineRunner implements CommandLineRunner {

    private final SyncInstanceToEs syncInstanceToEs;

    private final CanalEntity canalEntity;

    @Override
    public void run(String... args) throws Exception {
        if (canalEntity.isHasSyncInstanceToEs()) {
            log.info("实例数据同步到es");
            syncInstanceToEs.run();
        }
    }
}