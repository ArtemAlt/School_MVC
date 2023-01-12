package com.example.school_mvc.service;

import com.example.school_mvc.entity.Grade;
import com.example.school_mvc.entity.Student;
import com.example.school_mvc.entity.Theme;
import com.example.school_mvc.repository.GradleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class GradeService {

    private final GradleRepository repository;


    public GradeService(GradleRepository repository) {
        this.repository = repository;
    }

    public Grade getGrade(Student s, LocalDate date, Theme theme){
        return repository.getByDateAndTheme(s,date,theme);
    }

    public void getAll() {
        repository.getAll();
    }
}
