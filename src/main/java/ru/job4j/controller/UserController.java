package ru.job4j.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.User;
import ru.job4j.repository.springdata.AuthoritySpringDataRepository;
import ru.job4j.repository.springdata.UserSpringDataRepository;

/**
 * контроллер занимаеться обработкой видов авторизации
 */
@RequiredArgsConstructor
@Controller
public class UserController {

    private final PasswordEncoder encoder;
    private final UserSpringDataRepository users;
    private final AuthoritySpringDataRepository authorities;

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username or Password is incorrect !!";
        }
        if (logout != null) {
            errorMessage = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }

    /**
     * перевод на страницу регистрации пользователя
     * @param error
     * @param model errorMessage
     * @return String
     */
    @GetMapping("/registration")
    public String registrationPage(@RequestParam(value = "error", required = false) String error,
                                   Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Логин уже используется";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationSave(@ModelAttribute User user) {
        var role = this.authorities.findByAuthority("ROLE_USER");
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(role);
        user.setEnabled(true);
        this.users.save(user);
        return "redirect:/login";
    }


}

