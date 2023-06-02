package com.example.learn.framework.common.util.object;

import com.example.learn.framework.common.pojo.PageParam;

/**
 * {@link com.example.learn.framework.common.pojo.PageParam} 工具类
 *
 * @author wfh
 * @create 2023/6/2 15:45
 */
public class PageUtils {

    public static int getStart(PageParam pageParam) {
        return (pageParam.getPageNo() - 1) * pageParam.getPageSize();
    }

}
