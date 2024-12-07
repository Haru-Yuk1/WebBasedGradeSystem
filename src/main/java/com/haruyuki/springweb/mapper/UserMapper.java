package com.haruyuki.springweb.mapper;

import com.haruyuki.springweb.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    //登录操作
    @Select("select count(*) from user where username=#{username}")
    int selectByUsername(@Param("username") String username);
    @Select("select * from user where username=#{username} and password=#{password}")
    public User login(String username, String password);


    //注册操作
    @Insert("insert into user(username,password,role) values(#{username},#{password},#{role})")
    public int register(String username, String password, Integer role);
}
