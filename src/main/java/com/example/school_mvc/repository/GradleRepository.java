package com.example.school_mvc.repository;

import com.example.school_mvc.entity.Grade;
import com.example.school_mvc.entity.Student;
import com.example.school_mvc.entity.Theme;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class GradleRepository {
    HashMap<Student, List<Grade>> repository = new HashMap<>();


    public HashMap<Student, List<Grade>> getAll() {
        if (repository.isEmpty()) {
            init();
        }
        return repository;
    }

    private void init() {
        Student s1 = new Student(1, "Иванов", "Иван", "Иванович");
        Student s2 = new Student(2, "Петров", "Пётр", "Петрович");
        Student s3 = new Student(3, "Сидоров", "Сидор", "Сидорович");
        Theme t1 = new Theme("Литература");
        Theme t2 = new Theme("Биология");
        Theme t3 = new Theme("Математика");
        Grade g1 = new Grade(5, t1, LocalDate.now());
        Grade g2 = new Grade(5, t2, LocalDate.now());
        Grade g3 = new Grade(5, t3, LocalDate.now());
        repository.put(s1, Arrays.asList(g1, g2, g3));
        repository.put(s2, Arrays.asList(g1, g2, g3));
    }

    public Grade getByDateAndTheme(Student s, LocalDate date, Theme theme) {
        if (repository.isEmpty()) {
            init();
        }
        return repository.get(s).stream()
                .filter(g -> g.getDate() == date)
                .filter(g -> g.getTheme() == theme)
                .findFirst().orElse(new Grade());

    }

}
