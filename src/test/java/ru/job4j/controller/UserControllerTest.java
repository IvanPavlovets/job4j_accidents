package ru.job4j.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.Main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * 1) @SpringBootTest(classes = Main.class)
 * @AutoConfigureMockMvc эти две анатации создают котекст.
 * 2) MockMvc - Создает объект-заглушку. Мы можем отправлять
 * в него запросы.
 * 3) @WithMockUser - Подставляет авторизованного пользователя
 * в запрос.
 * 4) в тестах делаем запрос. Проверяем статус и вид.
 */
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenGetLoginPageThenStatusOk() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void whenGetRegistrationPageThenStatusOk() throws Exception {
        this.mockMvc.perform(get("/registration"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }


}
