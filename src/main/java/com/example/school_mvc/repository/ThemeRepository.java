package com.example.school_mvc.repository;

import com.example.school_mvc.entity.Student;
import com.example.school_mvc.entity.Theme;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThemeRepository {
    private List<Theme> repository = new ArrayList<>();
    ;

    public List<Theme> getAll() {
        if (repository.isEmpty()) {
            init();
        }
        return repository;
    }

    private void init() {
        Theme t1 = new Theme("Литература");
        Theme t2 = new Theme("Биология");
        Theme t3 = new Theme("Математика");
        repository.add(t1);
        repository.add(t2);
        repository.add(t3);
    }

}
