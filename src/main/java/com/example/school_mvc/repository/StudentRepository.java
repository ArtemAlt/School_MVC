package com.example.school_mvc.repository;

import com.example.school_mvc.entity.Student;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentRepository {
    private final HashMap<Integer, Student> repository = new HashMap<>();

    public Set<Student> getAll() {
        if (repository.isEmpty()) {
            init();
        }
        Set<Student> set = new HashSet<>();
        repository.forEach((key, value) -> set.add(value));
        return set.stream()
                .sorted(Comparator.comparing(Student::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private void init() {
        Student s1 = new Student(1, "Иванов", "Иван", "Иванович");
        Student s2 = new Student(2, "Петров", "Пётр", "Петрович");
        Student s3 = new Student(3, "Сидоров", "Сидор", "Сидорович");
        repository.put(s1.getId(), s1);
        repository.put(s2.getId(), s2);
        repository.put(s3.getId(), s3);
    }

    public Student save(Student s) {
        Integer maxId = repository.keySet()
                .stream()
                .max(Comparator.naturalOrder())
                .orElseThrow(RuntimeException::new);
        s.setId(maxId + 1);
        repository.put(s.getId(), s);
        return s;
    }

    public Student getById(Integer id) {
        return repository.get(id);
    }

    public void update(Student s) {
        repository.put(s.getId(), s);
    }
}
