package com.haruyuki.springweb.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("admin")
public class Admin extends User{
    private String adminId;
}
