package ru.job4j.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.Main;

import ru.job4j.model.Authority;
import ru.job4j.model.User;
import ru.job4j.repository.springdata.AuthoritySpringDataRepository;
import ru.job4j.service.springdata.UserSpringDataService;


import java.util.Optional;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 1) @SpringBootTest(classes = Main.class)
 * @AutoConfigureMockMvc эти две анатации создают котекст.
 * 2) MockMvc - Создает объект-заглушку. Мы можем отправлять
 * в него запросы.
 * 3) @WithMockUser - Подставляет авторизованного пользователя
 * в запрос.
 * 4) в тестах делаем запрос. Проверяем статус и вид.
 */

@Transactional
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class UserControllerTest {
    @MockBean
    private UserSpringDataService users;
    @MockBean
    private AuthoritySpringDataRepository authorities;
    @Autowired
    private MockMvc mockMvc;

    /**
     * Проверка метода registrationSave из UserController
     * @throws Exception
     */
    @Test
    @WithMockUser
    void whenRegistrationSaveThenRedirectLoginPage() throws Exception {
        var authority = new Authority(1, "ROLE_USER");
        var user = new User(0, "password", "login", authority, true);

        when(authorities.findByAuthority(authority.getAuthority())).thenReturn(authority);
        when(users.save(user)).thenReturn(Optional.of(user));

        this.mockMvc.perform(post("/registration")
                        .param("username", "login")
                        .param("password", "password"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    /**
     * Проверка метода loginPage из UserController
     * @throws Exception
     */
    @Test
    public void whenGetLoginPageThenStatusOk() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    /**
     * Проверка метода registrationPage из UserController
     * @throws Exception
     */
    @Test
    public void whenGetRegistrationPageThenStatusOk() throws Exception {
        this.mockMvc.perform(get("/registration"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }

    /**
     * Проверка метода registrationPage из UserController
     * @throws Exception
     */
    @Test
    public void whenlogoutPageThenRedirectLoginLogoutTrue() throws Exception {
        this.mockMvc.perform(get("/logout"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?logout=true"));
    }


}
