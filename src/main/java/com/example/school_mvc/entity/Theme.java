package com.example.school_mvc.entity;

import java.util.Objects;

public class Theme {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


    public Theme(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Theme{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theme theme = (Theme) o;
        return name.equals(theme.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
