package com.haruyuki.springweb.service;

import com.haruyuki.springweb.entity.Teacher;
import com.haruyuki.springweb.mapper.TeacherMapper;
import com.haruyuki.springweb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    public Result loginTeacher(String id, String password){
        Teacher teacher = teacherMapper.selectByteaId(id);
        if (teacher == null) {
            return Result.error().message("不存在该账户").code(20001);
        }
        if (!teacher.getPassword().equals(password)) {
            return Result.error().message("密码错误").code(20002);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("name", teacher.getName());
        data.put("id", id);
        data.put("gender", teacher.getGender());
        data.put("role", 1);

        return Result.ok().data(data).message("登录成功").code(20000);
    }

    public Result registerTeacher(String id, String password) {
        Teacher teacher = teacherMapper.selectByteaId(id);
        if (teacher == null) {
            return Result.error().message("用户不存在").code(20001);
        }
        if (teacher.getStatus() == 1) {
            return Result.error().message("该账号已被激活").code(20002);
        }

        int success = teacherMapper.activate(id, password, 1);
        if (success == 1) {
            return Result.ok().message("注册成功").code(20000);
        }
        return Result.error().message("注册失败").code(20002);
    }
}
