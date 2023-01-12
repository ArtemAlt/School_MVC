package com.example.school_mvc.entity;

import java.util.List;

public class StudentAndGrade {
    public Student student;

    public List<Grade> grades;

    public StudentAndGrade(Student student, List<Grade> grades) {
        this.student = student;
        this.grades = grades;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
