package com.example.demoasync.job;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author wfh
 * @create 2024/1/18 9:25
 */
@Component
public class MyJob implements Runnable{

    @Override
    @Async
    public void run() {
        try{
            for (int i = 0; i <10 ; i++) {
                System.out.println("============"+i);
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
