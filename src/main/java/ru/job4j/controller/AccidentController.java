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
import ru.job4j.service.AccidentTypeService;

/**
 * Контроллер обрабатывает действия с моделью Accident
 *
 * @author Ivan Pavlovets
 */
@RequiredArgsConstructor
@Controller
public class AccidentController {

    private final AccidentService service;
    private final AccidentTypeService types;

    /**
     * Обрабатывает переход на createAccident.html
     * @param model обёртка над классом HttpServletRequest.
     *              Можно получить данные из запроса или
     *              отправить данные в ответ.
     * @return String представление createAccident
     */
    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("user", "Ivan Pavlovets");
        model.addAttribute("types", types.findAll());
        return "createAccident";
    }

    /**
     * Обрабатывает добавление данных в accident
     * и их сохранение в store.
     * @param accident обьект правонарушения
     * @return String перенаправляет на вид index
     */
    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        service.add(accident);
        return "redirect:/index";
    }

    /**
     * Обработывает переход на editAccident.html
     * @param id редактируемого обьекта Accident,
     *          передаеться через параметр запроса
     * @param model
     * @return String
     */
    @GetMapping("/formEditAccident")
    public String viewEditAccident(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", "Ivan Pavlovets");
        model.addAttribute("editableAccident", service.findById(id));
        model.addAttribute("types", types.findAll());
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
