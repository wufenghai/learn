package com.example.learndocker;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wfh
 * @create 2023/4/17 10:02
 */
@Getter
@AllArgsConstructor
public enum Test {

    OK(200, "成功"),
    FAIL(500, "失败");

    private Integer code;

    private String message;
}
