package com.haruyuki.springweb.service;

import com.haruyuki.springweb.entity.Student;
import com.haruyuki.springweb.mapper.StudentMapper;
import com.haruyuki.springweb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public Result registerStudent(String id, String password) {
        Student student = studentMapper.selectByStuId(id);
        if (student == null) {
            return Result.error().message("用户不存在").code(20001);
        }
        if (student.getStatus() == 1) {
            return Result.error().message("该账号已被激活").code(20002);
        }

        int success = studentMapper.activate(id, password, 1);
        if (success == 1) {
            return Result.ok().message("注册成功").code(20000);
        }
        return Result.error().message("注册失败").code(20002);
    }

    public Result loginStudent(String id,String password){
        Student student=studentMapper.selectByStuId(id);
        if (student==null){
            return Result.error().message("不存在该账户").code(20001);
        }
        if (student.getStatus() == 0) {
            return Result.error().message("该账号未被激活").code(20002);
        }
        if (!student.getPassword().equals(password)){
            return Result.error().message("密码错误").code(20002);
        }
        Map<String, Object> data=new HashMap<>();
        data.put("name",student.getName());
        data.put("id", id);
        data.put("gender",student.getGender());
        data.put("role",0);

        return Result.ok().data(data).message("登录成功").code(20000);
    }
}
