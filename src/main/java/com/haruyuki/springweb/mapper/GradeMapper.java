package com.haruyuki.springweb.mapper;

import com.haruyuki.springweb.entity.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface GradeMapper {
    //根据学号查询学生的成绩
    @Select("select * from grade natural join courseclass natural join course where stuId=#{stuId}")
    public ArrayList<Grade> getGradeByStuId(String stuId);

    //根据课程号查询学生的成绩

}
