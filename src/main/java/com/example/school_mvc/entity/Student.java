package com.example.school_mvc.entity;

import java.util.Objects;

public class Student {

    public Student(Integer id, String name, String seName, String secondSeName) {
        this.id = id;
        this.name = name;
        this.seName = seName;
        this.secondSeName = secondSeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    private Integer id;

    private String name;
    private String seName;

    private String secondSeName;



    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeName() {
        return seName;
    }

    public void setSeName(String seName) {
        this.seName = seName;
    }

    public String getSecondSeName() {
        return secondSeName;
    }

    public void setSecondSeName(String secondSeName) {
        this.secondSeName = secondSeName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(seName, student.seName) && Objects.equals(secondSeName, student.secondSeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, seName, secondSeName);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
