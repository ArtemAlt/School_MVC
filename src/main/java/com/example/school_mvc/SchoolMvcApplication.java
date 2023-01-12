package com.example.school_mvc;

import com.example.school_mvc.repository.StudentRepository;
import com.example.school_mvc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SchoolMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolMvcApplication.class, args);
    }

}
