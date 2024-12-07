package com.haruyuki.springweb.mapper;

import com.haruyuki.springweb.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface CourseMapper {
    @Select("select * from course")
    ArrayList<Course> selectAll();
}
