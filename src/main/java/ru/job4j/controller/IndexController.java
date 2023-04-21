package ru.job4j.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.service.AccidentService;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final AccidentService service;

    @GetMapping("/index")
    public String getIndex(Model model) {
        model.addAttribute("user", "Ivan Pavlovets");
        model.addAttribute("accidents", service.findAll());
        return "index";
    }
}