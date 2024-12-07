package com.haruyuki.springweb.entity;


import lombok.Data;

@Data
public class User {
    private Integer id;
    private String gender;
    private String name;
    private String password;
    private Integer status; //是否激活
}
