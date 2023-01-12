package com.example.school_mvc.entity;

import java.time.LocalDate;

public class SelectDate {
    public LocalDate date;

    public SelectDate(LocalDate date) {
        this.date = date;
    }

    public SelectDate() {
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
