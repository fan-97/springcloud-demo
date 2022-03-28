package com.fanjie.helloserviceapi.dto;

import lombok.Data;

/**
 * @author fanjie
 * @date 2022/3/25 10:05
 */
@Data
public class User {
    private String name;
    private String password;
    private String userName;
    private int sex;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
