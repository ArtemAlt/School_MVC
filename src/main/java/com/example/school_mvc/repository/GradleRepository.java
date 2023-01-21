package com.example.school_mvc.repository;

import com.example.school_mvc.entity.Grade;
import com.example.school_mvc.entity.Student;
import com.example.school_mvc.entity.Theme;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class GradleRepository {
    private HashMap<Student, List<Grade>> repository;

    public GradleRepository() {
        this.repository = new HashMap<>();
        init();
    }


    public HashMap<Student, List<Grade>> getAll() {
        if (repository.isEmpty()) {
            init();
        }
        return repository;
    }

    private void init() {
        for (int i = 1; i < 10; i++) {
           Student s = new Student(i, "Name " + i, "SeName" + i, "SecondName" + i);
           repository.put(s,generateFiveRandomGrades());
        }
    }

    private List<Grade> generateFiveRandomGrades(){
        List<Grade> result= new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            result.add(new Grade(getRandomGrade(), getRandomTheme(), getRandomDate()));
        }
        return result;
    }

    private Theme getRandomTheme() {
        List<Theme> themes = getThemes();
        Theme result;
        int i = getRandomGrade();
        switch (i) {
            case 5:
                result = themes.get(0);
                break;
            case 4:
                result = themes.get(1);
                break;
            case 3:
                result = themes.get(2);
                break;
            default:
                result = new Theme("Theme default");
        }
        return result;
    }

    private LocalDate getRandomDate() {
        LocalDate result;
        int i = getRandomGrade();
        switch (i) {
            case 5:
                result = LocalDate.now();
                break;
            case 4:
                result = LocalDate.now().minusDays(1);
                break;
            case 3:
                result = LocalDate.now().minusDays(2);
                break;
            default:
                result = LocalDate.now().minusDays(3);
        }
        return result;
    }

    private List<Theme> getThemes() {
        List<Theme> list = new ArrayList<>();
        Theme t1 = new Theme("Литература");
        Theme t2 = new Theme("Биология");
        Theme t3 = new Theme("Математика");
        list.add(t1);
        list.add(t2);
        list.add(t3);
        return list;
    }

    private int getRandomGrade() {
        return (int) ((Math.random() * (6 - 3)) + 3);
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

    public List<Grade> getGradesByStudentId(Integer id) {
        Student s = repository.keySet()
                .stream()
                .filter(st -> st.getId().equals(id))
                .findFirst()
                .orElseThrow(RuntimeException::new);
        return repository.get(s);
    }

    public Theme getTheme() {
        return getThemes().get(0);
    }
}
