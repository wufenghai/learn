package com.example.learndocker.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举类测试
 * @author wfh
 * @create 2023/4/17 10:02
 */
@Getter
@AllArgsConstructor
public enum EnumTest {

    OK(200, "成功"),
    FAIL(500, "失败");

    private Integer code;

    private String message;
}
