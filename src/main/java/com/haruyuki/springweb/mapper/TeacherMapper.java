package com.haruyuki.springweb.mapper;

import com.haruyuki.springweb.entity.Student;
import com.haruyuki.springweb.entity.StudentWithGrade;
import com.haruyuki.springweb.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface TeacherMapper {
    @Select("select * from teacher where teacherId=#{teacherId}")
    public Teacher selectByteaId(String teacherId);

    @Update("update teacher set password=#{password} ,status=#{status} where teacherId=#{teacherId}")
    public int activate(String teacherId, String password, Integer status);

    @Select("select * from student natural join courseclass natural join grade natural join course where teacherId=#{teacherId}")
    public ArrayList<StudentWithGrade> getStudentByTeacherId(String teacherId);
}
