package com.haruyuki.springweb.controller;

import com.haruyuki.springweb.entity.Admin;
import com.haruyuki.springweb.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(maxAge = 36000)
public class AdminController {
    @Autowired
    private AdminMapper adminMapper;



}
