package com.example.school_mvc.controller;


import com.example.school_mvc.entity.Student;
import com.example.school_mvc.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("students")
public class StudentController {
    private final StudentService service;


    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping()
    public String viewJournal(Model model) {
        model.addAttribute("studentList", service.getAllStudent());
        return "students";
    }

    @GetMapping("/new")
    public String viewNewStudent(Model model) {
        model.addAttribute("student", new Student());
        return "studentNew";
    }

    @PostMapping("/edit")
    public String editStudent(@RequestParam(name = "stId") Integer id, Model model) {
        Student s = service.getById(id);
        model.addAttribute("student", s);
        return "studentEdit";
    }

    @PostMapping("/edit_save")
    public RedirectView updateStudent(@ModelAttribute("student") Student s) {
        final RedirectView redirectView = new RedirectView("/students", true);
        System.out.println(s.toString());
        service.updateStudent(s);
        return redirectView;
    }

    @PostMapping("/new")
    public RedirectView addNew(@ModelAttribute("student") Student s, RedirectAttributes redirect) {
        final RedirectView redirectView = new RedirectView("/students/new", true);
        Student savedStudent = service.addNew(s);
        redirect.addFlashAttribute("savedStudent", savedStudent);
        redirect.addFlashAttribute("addStudentSuccess", true);
        return redirectView;
    }
}
