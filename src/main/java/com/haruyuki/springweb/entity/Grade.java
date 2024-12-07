package com.haruyuki.springweb.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Grade {
    private String stuId;
    private String classId;
    private String courseName;
    private Integer regularScore; // 平时成绩
    private Integer midtermScore; // 期中成绩
    private Integer experimentScore; // 实验成绩
    private Integer finalScore; // 期末成绩
    private Integer ComprehensiveScore; // 综合成绩

}
