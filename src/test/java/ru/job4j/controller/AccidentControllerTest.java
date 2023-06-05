package ru.job4j.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.Main;
import ru.job4j.model.Accident;
import ru.job4j.model.AccidentType;
import ru.job4j.service.springdata.AccidentSpringDataService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Transactional
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class AccidentControllerTest {
    @MockBean
    private AccidentSpringDataService accidents;

    @Autowired
    private MockMvc mockMvc;

    /**
     * Проверка метода save из AccidentController
     * @throws Exception
     */
    @Test
    @WithMockUser
    void whenSaveAccidentThenRedirectIndexPage() throws Exception {
        var rIds = Set.of(1, 2, 3);
        var accident = new Accident(0, "11", "22", "33",
                new AccidentType(2, "Машина и человек"), Collections.emptySet());
        this.mockMvc.perform(post("/saveAccident")
                        .param("name", accident.getName())
                        .param("text", accident.getText())
                        .param("address", accident.getAddress())
                        .param("type", "2")
                        .param("rIds", "1", "2", "3"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));

        ArgumentCaptor<Accident> accidentCapture = ArgumentCaptor.forClass(Accident.class);
        ArgumentCaptor<Set<Integer>> rIdsCapture = ArgumentCaptor.forClass(Set.class);

        verify(accidents).add(accidentCapture.capture(), rIdsCapture.capture());

        assertThat(accidentCapture.getValue()).usingRecursiveComparison()
                .isEqualTo(accident);
        assertThat(rIdsCapture.getValue()).usingRecursiveComparison()
                .isEqualTo(rIds);
    }

    /**
     * Проверка метода update из AccidentController
     * @throws Exception
     */
    @Test
    @WithMockUser
    void whenEditAccidentThenRedirectIndexPage() throws Exception {
        var rIds = Set.of(1, 2, 3);
        var accident = new Accident(0, "11", "22", "33",
                new AccidentType(2, "Машина и человек"), Collections.emptySet());
        this.mockMvc.perform(post("/editAccident")
                        .param("id", "0")
                        .param("name", "11")
                        .param("text", "22")
                        .param("address", "33")
                        .param("type", "2")
                        .param("rIds", "1", "2", "3"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));

        ArgumentCaptor<Accident> accidentCapture = ArgumentCaptor.forClass(Accident.class);
        ArgumentCaptor<Set<Integer>> rIdsCapture = ArgumentCaptor.forClass(Set.class);

        verify(accidents).update(accidentCapture.capture(), rIdsCapture.capture());

        assertThat(accidentCapture.getValue()).usingRecursiveComparison()
                .isEqualTo(accident);
        assertThat(rIdsCapture.getValue()).usingRecursiveComparison()
                .isEqualTo(rIds);
    }

    /**
     * Проверка метода deleteAccident из AccidentController
     * @throws Exception
     */
    @Test
    @WithMockUser
    void whenGetDeleteAccidentTrueThenReturnListPage() throws Exception {
        this.mockMvc.perform(get("/delete?id=" + anyInt()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));
    }

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
