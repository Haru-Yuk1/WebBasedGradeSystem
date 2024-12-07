package com.haruyuki.springweb.controller;

import com.haruyuki.springweb.entity.StudentWithGrade;
import com.haruyuki.springweb.entity.Teacher;
import com.haruyuki.springweb.mapper.TeacherMapper;
import com.haruyuki.springweb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin(maxAge = 36000)
public class TeacherController {
    @Autowired
    private TeacherMapper teacherMapper;

    @PostMapping("/getStudentByteacherId")
    public Result getStudentByteacherId(@RequestParam String id){
        Teacher teacher=teacherMapper.selectByteaId(id);
        if (teacher==null){
            return Result.error().message("未找到该教师").code(20001);
        }
        ArrayList<StudentWithGrade> studentWithGrades=teacherMapper.getStudentByTeacherId(id);
        if (studentWithGrades==null){
            return Result.error().message("未找到该教师的学生").code(20002);
        }
        return Result.ok().data("studentWithGrades",studentWithGrades).message("获取学生信息成功").code(20000);

    }
}
