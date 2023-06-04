package com.example.lab04.validation.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserUpdateDTO {

    /**
     * 用户编号
     */
    @NotNull(message = "{UserUpdateDTO.id.NotNull}")
    private Integer id;


}