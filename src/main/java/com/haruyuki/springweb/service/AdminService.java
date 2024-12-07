package com.haruyuki.springweb.service;

import com.haruyuki.springweb.entity.Admin;
import com.haruyuki.springweb.mapper.AdminMapper;
import com.haruyuki.springweb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public Result loginAdmin(String id, String password) {
        Admin admin = adminMapper.selectByAdminId(id);
        if (admin == null) {
            return Result.error().message("不存在该账户").code(20001);
        }
        if (!admin.getPassword().equals(password)) {
            return Result.error().message("密码错误").code(20002);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("name", admin.getName());
        data.put("id", id);
        data.put("gender", admin.getGender());
        data.put("role", 2);

        return Result.ok().data(data).message("登录成功").code(20000);
    }

    public Result registerAdmin(String id, String password) {
        Admin admin = adminMapper.selectByAdminId(id);
        if (admin == null) {
            return Result.error().message("用户不存在").code(20001);
        }
        if (admin.getStatus() == 1) {
            return Result.error().message("该账号已被激活").code(20002);
        }

        int success = adminMapper.activate(id, password, 1);
        if (success == 1) {
            return Result.ok().message("注册成功").code(20000);
        }
        return Result.error().message("注册失败").code(20002);
    }

}
