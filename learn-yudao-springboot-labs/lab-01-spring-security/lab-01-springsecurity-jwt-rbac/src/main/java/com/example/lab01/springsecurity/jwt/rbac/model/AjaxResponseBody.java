package com.example.lab01.springsecurity.jwt.rbac.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wfh
 * @create 2023/4/18 17:08
 */
@Data
public class AjaxResponseBody implements Serializable {

    private String status;
    private String msg;
    private Object result;
    private String jwtToken;
}
