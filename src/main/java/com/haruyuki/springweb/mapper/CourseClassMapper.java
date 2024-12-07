package com.haruyuki.springweb.mapper;

import com.haruyuki.springweb.entity.CourseClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface CourseClassMapper {
    @Select("select * from courseclass")
    public ArrayList<CourseClass> selectAllCourseClass();
}
