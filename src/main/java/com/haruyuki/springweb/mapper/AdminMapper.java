package com.haruyuki.springweb.mapper;

import com.haruyuki.springweb.entity.Admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper {
    @Select("select * from admin where adminId=#{adminId}")
    public Admin selectByAdminId(String adminId);

    @Update("update admin set password=#{password} ,status=#{status} where adminId=#{adminId}")
    public int activate(String adminId, String password, Integer status);

}
