package com.example.school_mvc.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TimeService {
    LocalDate currentDate;
    public TimeService() {
        currentDate = LocalDate.now();
    }

    public LocalDate getDate(){
        return currentDate;
    }
}
