package ru.job4j.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.service.springdata.AccidentSpringDataService;

/**
 * Контроллер стартовой страницы
 *
 * После авторизации Spring создает объект SecurityContextHolder
 * в котором держит информацию об авторизованном пользователе.
 * По аналогии HttpSession в Servlet.
 *
 * @author Ivan Pavlovets
 */
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final AccidentSpringDataService service;

    @GetMapping("/index")
    public String getIndex(Model model) {
        model.addAttribute("user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("accidents", service.findAll());
        return "index";
    }
}
