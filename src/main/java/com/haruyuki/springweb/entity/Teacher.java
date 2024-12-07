package com.haruyuki.springweb.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("teacher")
public class Teacher extends User{
    private String teacherId;

}

