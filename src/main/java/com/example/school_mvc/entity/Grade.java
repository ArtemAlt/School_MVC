package com.example.school_mvc.entity;

import java.time.LocalDate;

public class Grade {
    private Integer value;
    private Theme theme;
    private LocalDate date;


    public Grade(Integer value, Theme theme, LocalDate date) {
        this.value = value;
        this.theme = theme;
        this.date = date;
    }

    public Grade() {
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "value=" + value +
                ", theme=" + theme.getName() +
                ", date=" + date.toString() +
                '}';
    }
}
