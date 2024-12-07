package com.haruyuki.springweb.controller;

import com.haruyuki.springweb.mapper.CourseMapper;
import com.haruyuki.springweb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(maxAge = 36000)
public class CourseController {
    @Autowired
    private CourseMapper courseMapper;

    @PostMapping("/getCourses")
    public Result getCourses() {
        return Result.ok().data("courses", courseMapper.selectAll()).message("获取班级信息成功").code(20000);
    }
}
