package com.haruyuki.springweb.mapper;

import com.haruyuki.springweb.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StudentMapper {
    @Select("select * from student where stuId=#{stuId}")
    public Student selectByStuId(String stuId);

    @Update("update student set password=#{password} ,status=#{status} where stuId=#{stuId}")
    public int activate(String stuId, String password, Integer status);



}
