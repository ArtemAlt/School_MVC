package com.example.school_mvc.controller;


import com.example.school_mvc.service.ThemeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("themes")
public class ThemeController {
private final ThemeService service;


    public ThemeController(ThemeService themeService) {
        this.service = themeService;
    }

    @GetMapping()
    public String viewJournal(Model model){
        model.addAttribute("themeList",service.getAll());
        return "themes";
    }
}
