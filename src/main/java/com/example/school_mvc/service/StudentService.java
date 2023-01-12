package com.example.school_mvc.service;

import com.example.school_mvc.entity.Grade;
import com.example.school_mvc.entity.Student;
import com.example.school_mvc.entity.StudentAndGrade;
import com.example.school_mvc.repository.GradleRepository;
import com.example.school_mvc.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final GradleRepository gradleRepository;

    public StudentService(StudentRepository repository, GradleRepository gradleRepository) {
        this.repository = repository;
        this.gradleRepository = gradleRepository;
    }

    public Set<Student> getAllStudent() {
        return repository.getAll();
    }

    public List<StudentAndGrade> getAll() {
        List<StudentAndGrade> answer = new ArrayList<>();
        HashMap<Student, List<Grade>> grades = gradleRepository.getAll();
        grades.forEach((s, g) -> answer.add(new StudentAndGrade(s, g)));
        return answer;
    }

    public Student addNew(Student s) {
        return repository.save(s);
    }

    public Student getById(Integer id) {
        return repository.getById(id);
    }

    public void updateStudent(Student s) {
        repository.update(s);
    }
}
