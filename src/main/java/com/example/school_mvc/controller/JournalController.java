package com.example.school_mvc.controller;


import com.example.school_mvc.entity.SelectDate;
import com.example.school_mvc.entity.SelectTheme;
import com.example.school_mvc.service.GradeService;
import com.example.school_mvc.service.StudentService;
import com.example.school_mvc.service.ThemeService;
import com.example.school_mvc.service.TimeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("journal")
public class JournalController {
private final StudentService service;
private final GradeService gradeService;
private final ThemeService themeService;
private final TimeService timeService;

    public JournalController(StudentService service, GradeService gradeService, ThemeService themeService, TimeService timeService) {
        this.service = service;
        this.gradeService = gradeService;
        this.themeService = themeService;
        this.timeService = timeService;
    }

    @GetMapping()
    public String viewJournal(@ModelAttribute(value = "selectedTheme") SelectTheme st,
                              Model model){
        model.addAttribute("studentList",service.getAll());
        model.addAttribute("themeList",themeService.getAll());
        model.addAttribute("currentDate", timeService.getDate());
        System.out.println("Selected theme - "+st.getName());
//        System.out.println("Date - "+sd.getDate().toString());
        return "journal";
    }

}
