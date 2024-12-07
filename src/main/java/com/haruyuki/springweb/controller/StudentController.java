package com.haruyuki.springweb.controller;

import com.haruyuki.springweb.entity.Grade;
import com.haruyuki.springweb.entity.Student;
import com.haruyuki.springweb.entity.StudentWithGrade;
import com.haruyuki.springweb.entity.Teacher;
import com.haruyuki.springweb.mapper.GradeMapper;
import com.haruyuki.springweb.mapper.StudentMapper;
import com.haruyuki.springweb.mapper.TeacherMapper;
import com.haruyuki.springweb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(maxAge = 36000)
public class StudentController {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private GradeMapper gradeMapper;



    @PostMapping("/getStudentGrades")
    public Result getStudentGrades(@RequestParam String id) {
        Student student = studentMapper.selectByStuId(id);
        if(student==null) {
            return Result.error().message("未找到该学生").code(20001);
        }
        ArrayList<Grade> grades=gradeMapper.getGradeByStuId(id);
        if (grades==null) {
            return Result.error().message("未找到该学生的成绩").code(20002);
        }

        Map<String, Object> data=new HashMap<>();
        data.put("grades",grades);
        return Result.ok().data(data).message("获取学生成绩成功").code(20000);
    }


    @PostMapping("/test")
    public String test() {
        Student student=studentMapper.selectByStuId("20221212");
        return "test";
    }
}
