package ru.job4j.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.Main;
import ru.job4j.model.Accident;
import ru.job4j.service.springdata.AccidentSpringDataService;

import java.util.Collections;
import java.util.Optional;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class AccidentControllerTest {
    @MockBean
    private AccidentSpringDataService accidents;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void whenGetCreateAccidentPageThenStatusOk() throws Exception {
        this.mockMvc.perform(get("/createAccident"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("createAccident"));
    }

    @Test
    @WithMockUser
    public void whenGetEditAccidentPageThenStatusOk() throws Exception {
        var accident = new Accident(11, "22", "33", "44", null, Collections.emptySet());
        when(accidents.findById(accident.getId())).thenReturn(Optional.of(accident));
        this.mockMvc.perform(get("http://localhost:8080/editAccident?id=" + accident.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("editAccident"));
    }

}
