package com.example.lab04.validation.dto;

import lombok.Data;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Data
public class UserUpdateStatusDTO {

    /**
     * 分组 01 ，要求状态必须为 true
     */
    public interface Group01 {}

    /**
     * 状态 02 ，要求状态必须为 false
     */
    public interface Group02 {}

    /**
     * 状态
     */
    @AssertTrue(message = "状态必须为 true", groups = Group01.class)
    @AssertFalse(message = "状态必须为 false", groups = Group02.class)
    private Boolean status;
}
