package com.example.school_mvc.repository;

import com.example.school_mvc.entity.Grade;
import com.example.school_mvc.entity.Student;
import com.example.school_mvc.entity.Theme;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class StudentRepository {
    private final HashMap<Integer, Student> repository;
    private final GradleRepository gradleRepository;

    public StudentRepository(GradleRepository gradleRepository) {
        repository = new HashMap<>();
        this.gradleRepository = gradleRepository;
    }

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

    /**
     * Поиск студента в репозитории по его оценке
     *
     * @param grade оценка
     */
    private Student getStudent(Grade grade) {
        return gradleRepository.getAll()
                .entrySet().stream()
                .filter(entry -> entry.getValue().contains(grade))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getKey();
    }

    /**
     * 1. Напиши поиск по фамилии студента на вхождение подобный оператору like "text%" (.filter)
     */
    public Student findStudentBySeNameLike(String name) {
        return gradleRepository.getAll().keySet()
                .stream()
                .filter(s -> s.getName().contains(name))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    /**
     * 2. Есть ли в 1оим из предметов студент у которого последняя оценка 5 (FindAny)
     */
    public String ifStudentInThemeWithLastGradeFive(Theme theme) {
        AtomicReference<String> result = new AtomicReference<>();
        Comparator<Grade> gradeDateComparator = Comparator.comparing(Grade::getDate);
        gradleRepository.getAll()
                .entrySet()
                .stream()
                .map((entry) -> {
                    String s = entry.getKey().getName();
                    Optional<Grade> filteredGrade = entry.getValue()
                            .stream()
                            .filter(g -> g.getTheme().equals(theme))
                            .sorted(gradeDateComparator)
                            .filter(g -> g.getValue().equals(5))
                            .findFirst();

                    if (filteredGrade.isPresent()) result.set(s);
                    return null;
                }).
                collect(Collectors.toSet());
        return result.get();
    }

    /**
     * 3. Есть ли студент, у которого по одному из предметов все оценки > 3 (FindAll)
     */
    public Set<String> findStudentWithAllGradesAboveThree(Theme theme) {
        Set<String> result = new HashSet<>();
        gradleRepository.getAll()
                .entrySet()
                .stream()
                .map((entry) -> {
                    String studName = entry.getKey().getName();
                    boolean b = entry.getValue()
                            .stream()
                            .filter(g -> g.getTheme().equals(theme))
                            .allMatch(grade -> grade.getValue().equals(3));
                    if (b) result.add(studName);
                    return null;
                }).collect(Collectors.toSet());
        return result;
    }

    /**
     * 4. Посчитай для каждого студента по каждому из предметов средний бал (reduce ????).
     * На выходе Map<Theme, List<Object>, Object = Student и средний бал
     */
//    public void countAverageGrades() {
//        Map<String, List<StudentAverageGrade>> averageGrades = new HashMap<>();
//        List<StudentAverageGrade> averageGradeList = new ArrayList<>();
//        Map<String, Map<String, Double>> testAverage = new HashMap<>();
//        gradleRepository
//                .getAll().entrySet()
//                .stream()
//                .map(entry -> {
//                    String s = entry.getKey().getName();
//                    AtomicReference<String> currentTheme = new AtomicReference<>();
//                    Map<String, Double> av = entry.getValue()
//                            .stream()
//                            .reduce()
//                            .collect(Collectors
//                                    .groupingBy((theme) -> {
//                                        currentTheme.set(theme.getTheme().getName());
//                                        return currentTheme.get();
//                                    }))
//                    averageGradeList.add(new StudentAverageGrade(s, av.get(currentTheme.get())));
//                    averageGrades.put(currentTheme.get(), averageGradeList);
//                    testAverage.put(s, av);
//
//                    return null;
//                }).collect(Collectors.toSet());
//        System.out.println(averageGrades);
//
//    }

    /**
     * 5. Такое же задание как в 4, но только реализация через Collectors.averagingDouble
     *
     * @return
     */
    public Map<Theme, List<StudentAverageGrade>> countAverageGradesV2() {
        Map<Theme, List<StudentAverageGrade>> averageGrades = new HashMap<>();
        List<StudentAverageGrade> averageGradeList = new ArrayList<>();
        gradleRepository
                .getAll().entrySet()
                .stream()
                .map(entry -> {
                    String s = entry.getKey().getName();
                    AtomicReference<Theme> currentTheme = new AtomicReference<>();
                    Map<Theme, Double> av = entry.getValue()
                            .stream()
                            .collect(Collectors
                                    .groupingBy((grade) -> {
                                        currentTheme.set(grade.getTheme());
                                        return currentTheme.get();
                                    }, Collectors.averagingDouble(Grade::getValue)));
                    averageGradeList.add(new StudentAverageGrade(s, av.get(currentTheme.get())));
                    averageGrades.put(currentTheme.get(), averageGradeList);
                    return averageGradeList;
                }).collect(Collectors.toSet());
        return averageGrades;
    }

    /**
     * 6. Сгруппируй по каждому предмету отсортированных студентов по фамилии (natural) в 3 группы
     * по оценкам значениям (sort, grouppingBy) На выходе Map<String, List<List>>
     * или Map<Theme, List<List>>  -   Theme - List<Grope<List<Student - random 3шт>>>
     */
    public Map<Theme, List<StudentGroup>> groupingByThemeSortedStudentNaturalOrderWithThreeStudent() {
        List<Student> students = new ArrayList<>(gradleRepository.getAll().keySet());
        Set<Theme> themes =  gradleRepository.getAll().values()
                .stream()
                .map(grades -> {
                            AtomicReference<Theme> currentTheme = new AtomicReference<>();
                            Map<Theme, List<Grade>> av = grades.stream()
                                    .collect(Collectors
                                            .groupingBy((grade) -> {
                                                currentTheme.set(grade.getTheme());
                                                return currentTheme.get();
                                            }));
                            return av.keySet();
                        }
                ).collect(Collectors.toSet())
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        List<List<Student>> groupedStudents = Lists.partition(students, 3);
        List<StudentGroup> studentGroups = groupedStudents.stream()
                .map(StudentGroup::new)
                .collect(Collectors.toList());
        List<List<StudentGroup>> partitionGroups = Lists.partition(studentGroups, studentGroups.size() / themes.size());
        Map<Theme, List<StudentGroup>> result = new HashMap<>();
        AtomicInteger counter = new AtomicInteger(0);
        themes.forEach((t) -> {
            result.put(t, partitionGroups.get(counter.get()));
            counter.getAndIncrement();
        });
        return result;
    }


/**
 * 7. Результат из 5ого пункта, расформируй студентов и убери дубликаты (flatMap, distinct)
 */

    /** 8. Сгруппируй студентов по среднему балу и каждому предмету (примени 4ый пункт).
     * В методе должен быть сразу return 3-4 4-5 List<Student> enum?*/


    /**
     * 9. Создай private метод, который принимает vararg (Theme...). В этом пункте реализуй передачу всех
     * предметов в этот приват метод.
     * А в приват методе на выходе должны быть отсортированные предметы Set
     */
    private Set<Theme> sortedThemes(Theme... themes) {
        Comparator<Theme> naturalOrderByName = Comparator.comparing(Theme::getName);
        return Arrays.stream(themes)
                .sorted(naturalOrderByName)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public void testListToVararg() {
        List<Theme> s = new ArrayList<>();
        Theme t1 = new Theme("Литература");
        Theme t2 = new Theme("Биология");
        Theme t3 = new Theme("Математика");
        Theme t4 = new Theme("Литература");
        Theme t5 = new Theme("Биология");
        Theme t6 = new Theme("Физика");
        s.add(t1);
        s.add(t2);
        s.add(t3);
        s.add(t4);
        s.add(t5);
        s.add(t6);
        System.out.println(sortedThemes(s.toArray(new Theme[0])));
    }

    private class StudentAverageGrade {
        String student;
        Double averageGrade;


        public StudentAverageGrade() {
        }

        public StudentAverageGrade(String student, Double averageGrade) {
            this.student = student;
            this.averageGrade = averageGrade;
        }


        public Double getAverageGrade() {
            return averageGrade;
        }

        public void setAverageGrade(Double averageGrade) {
            this.averageGrade = averageGrade;
        }

        public String getStudent() {
            return student;
        }

        public void setStudent(String student) {
            this.student = student;
        }

        @Override
        public String toString() {
            return "StudentAverageGrade{" +
                    "student='" + student + '\'' +
                    ", averageGrade=" + averageGrade +
                    '}';
        }
    }

    private class StudentGroup {

        List<Student> students;

        public StudentGroup(List<Student> students) {
            this.students = students;
        }

        public StudentGroup() {
        }

        public List<Student> getStudents() {
            return students;
        }

        public void setStudents(List<Student> students) {
            this.students = students;
        }

        @Override
        public String toString() {
            return "StudentGroup{" +
                    "students=" + students +
                    '}';
        }
    }
}
