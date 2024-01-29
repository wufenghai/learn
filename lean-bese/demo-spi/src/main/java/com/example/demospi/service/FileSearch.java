package com.example.demospi.service;

import java.util.List;

/**
 * @author wfh
 * @create 2024/1/29 11:47
 */
public class FileSearch implements Search {

    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("文件搜索 " + keyword);
        return null;
    }

}
