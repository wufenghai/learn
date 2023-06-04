package com.example.lab04.validation.core.validation;

// GenderEnum.java

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum GenderEnum implements IntArrayValuable {

    MALE(1, "男"),
    FEMALE(2, "女");

    /**
     * 值数组
     */
    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(GenderEnum::getValue).toArray();

    /**
     * 性别值
     */
    private final Integer value;
    /**
     * 性别名
     */
    private final String name;

    GenderEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }


    @Override
    public int[] array() {
        return ARRAYS;
    }

}