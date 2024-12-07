package com.haruyuki.springweb.controller;

import com.haruyuki.springweb.mapper.CourseClassMapper;
import com.haruyuki.springweb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(maxAge = 36000)
public class CourseClassController {
    @Autowired
    private CourseClassMapper courseClassMapper;

    @PostMapping("/getClasses")
    public Result getClasses() {
        return Result.ok().data("classes", courseClassMapper.selectAllCourseClass()).message("获取班级信息成功").code(20000);
    }
}
