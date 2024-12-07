package com.haruyuki.springweb.controller;



import com.haruyuki.springweb.service.AdminService;
import com.haruyuki.springweb.service.StudentService;
import com.haruyuki.springweb.service.TeacherService;
import com.haruyuki.springweb.util.JwtUtils;
import com.haruyuki.springweb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(maxAge = 36000)
public class LoginController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/login")
    public Result login(@RequestParam String id,@RequestParam String password){
        if (id.startsWith("T")){
            return teacherService.loginTeacher(id,password);
        }
        else if (id.startsWith("A")) {
            return adminService.loginAdmin(id,password);
        }
        else {
            return studentService.loginStudent(id, password);
        }
    }

    @PostMapping("/register")
    public Result register(@RequestParam String id, @RequestParam String password) {
        if (id.startsWith("T")){
            return teacherService.registerTeacher(id, password);
        }
        else if (id.startsWith("A")) {
            return adminService.registerAdmin(id, password);
        }
        else {
            return studentService.registerStudent(id, password);
        }
    }


}
