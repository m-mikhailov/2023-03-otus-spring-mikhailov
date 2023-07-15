package ru.mikhailov.otus.task9.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.mikhailov.otus.task9.domain.dto.AuthorCreateDto;
import ru.mikhailov.otus.task9.domain.dto.AuthorDto;
import ru.mikhailov.otus.task9.service.AuthorService;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Authors Controller Tests")
@WebMvcTest(AuthorsController.class)
public class AuthorsControllerTests {

    private final static List<AuthorDto> EXISTING_AUTHORS = List.of(
            new AuthorDto(1L, "Пушкин")
    );

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorService service;


    @Test
    @DisplayName("Should return add view")
    public void shouldReturnAddView() throws Exception {
        mvc.perform(get("/newauthor"))
                .andExpect(status().isOk())
                .andExpect(view().name("authors/add"));
    }

    @Test
    @DisplayName("Should return model authors and list view")
    public void shouldReturnAuthorsAndListView() throws Exception {
        given(service.findAll())
                .willReturn(EXISTING_AUTHORS);

        mvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(view().name("authors/list"))
                .andExpect(model().attribute("authors", EXISTING_AUTHORS));
    }

    @Test
    @DisplayName("Should create author and return redirect")
    public void shouldCreateAuthorAndReturnRedirect() throws Exception {
        mvc.perform(post("/authors")
                .param("name", "Пушкин"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/authors"));

        verify(service).create(eq(new AuthorCreateDto("Пушкин")));
    }


}
