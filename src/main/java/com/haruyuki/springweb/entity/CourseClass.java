package com.haruyuki.springweb.entity;

import lombok.Data;

@Data
public class CourseClass {
    private String classId; //教学班编号
    private String teacherId;   //教师编号
    private String courseId;    //课程编号
    private String semester;    //开课学期
    private int totalStudents;

}
