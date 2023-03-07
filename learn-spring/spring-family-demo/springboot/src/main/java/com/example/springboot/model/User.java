package com.example.springboot.model;

/**
 * @author wfh
 * @create 2023/2/28 17:12
 */
public class User {
    private String id;
    private String age;
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
