package com.example.demothread.juc.util.thread_local;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 阿里巴巴 java 开发手册中推荐的 ThreadLocal 的用法:
 *
 * @author wfh
 * @create 2024/1/31 18:17
 */
public class DateUtils {

    private static final ThreadLocal<DateFormat> df = new ThreadLocal<>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };


    public static void main(String[] args) {
        //然后我们再要用到 DateFormat 对象的地方，这样调用：
        System.out.println(DateUtils.df.get().format(new Date()));
    }

}
