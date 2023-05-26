package ru.job4j.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.model.Accident;
import ru.job4j.service.springdata.AccidentSpringDataService;
import ru.job4j.service.springdata.AccidentTypeSpringDataService;
import ru.job4j.service.springdata.RuleSpringDataService;

import java.util.Set;

/**
 * Контроллер обрабатывает действия с моделью Accident
 *
 * После авторизации Spring создает объект SecurityContextHolder
 * в котором держит информацию об авторизованном пользователе.
 * По аналогии HttpSession в Servlet.
 *
 * @author Ivan Pavlovets
 */
@RequiredArgsConstructor
@Controller
public class AccidentController {

    private final AccidentSpringDataService service;
    private final AccidentTypeSpringDataService types;
    private final RuleSpringDataService rules;

    /**
     * Обрабатывает переход на createAccident.html
     * @param model обёртка над классом HttpServletRequest.
     *              Можно получить данные из запроса или
     *              отправить данные в ответ.
     * @return String представление createAccident
     */
    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("types", types.findAll());
        model.addAttribute("rules", rules.findAll());
        return "createAccident";
    }

    /**
     * Обрабатывает добавление данных в accident
     * и их сохранение в store.
     * @param accident обьект правонарушения
     * @return String перенаправляет на вид index
     */
    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident,
                       @RequestParam(required = false) Set<Integer> rIds) {
        service.add(accident, rIds);
        return "redirect:/index";
    }

    @GetMapping("/delete")
    public String deleteAccident(@RequestParam("id") int id) {
        service.delete(id);
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
        model.addAttribute("user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("editableAccident", service.findById(id).get());
        model.addAttribute("types", types.findAll());
        model.addAttribute("rules", rules.findAll());
        return "editAccident";
    }

    /**
     * Сохраняет данные в accident
     * после редактирования
     * @param accident
     * @return String
     */
    @PostMapping("/editAccident")
    public String update(@ModelAttribute Accident accident,
                         @RequestParam(required = false) Set<Integer> rIds) {
        service.update(accident, rIds);
        return "redirect:/index";
    }

}
