package com.example.school_mvc.service;

import com.example.school_mvc.entity.Student;
import com.example.school_mvc.entity.Theme;
import com.example.school_mvc.repository.StudentRepository;
import com.example.school_mvc.repository.ThemeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService {

    private ThemeRepository repository;

    public ThemeService(ThemeRepository repository) {
        this.repository = repository;
    }

    public List<Theme> getAll(){
        return repository.getAll();
    }
}
