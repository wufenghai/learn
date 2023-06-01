package com.example.learn.framework.common.enums;

import com.example.learn.framework.common.core.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;


/**
 * 全局用户类型枚举
 */
@AllArgsConstructor
@Getter
public enum UserTypeEnum implements IntArrayValuable {

    MEMBER(1, "会员"),
    ADMIN(2, "管理员");

    private static final int[] ARRAY = Arrays.stream(values()).mapToInt(UserTypeEnum::getValue).toArray();
    /**
     * 类型
     */
    private final Integer value;

    /**
     * 类型名
     */
    private final String name;

    @Override
    public int[] array() {
        return new int[0];
    }
}
