package ru.job4j.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.model.Accident;
import ru.job4j.service.AccidentService;

@RequiredArgsConstructor
@Controller
public class AccidentController {

    private final AccidentService service;

    /**
     * Обрабатывает переход на createAccident.html
     * @param model
     * @return String
     */
    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("user", "Ivan Pavlovets");
        model.addAttribute("accident", new Accident());
        return "createAccident";
    }

    /**
     * Обрабатывает добавление данных в accident
     * и их сохранение в store.
     * @param accident
     * @return
     */
    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        service.add(accident);
        return "redirect:/index";
    }

    /**
     * Обработывает переход на editAccident.html
     * @param id
     * @param model
     * @return String
     */
    @GetMapping("/formEditAccident")
    public String viewEditAccident(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", "Ivan Pavlovets");
        model.addAttribute("editableAccident", service.findById(id));
        return "editAccident";
    }

    /**
     * Сохраняет данные в accident
     * после редактирования
     * @param accident
     * @return String
     */
    @PostMapping("/editAccident")
    public String update(@ModelAttribute Accident accident) {
        service.update(accident);
        return "redirect:/index";
    }

}
