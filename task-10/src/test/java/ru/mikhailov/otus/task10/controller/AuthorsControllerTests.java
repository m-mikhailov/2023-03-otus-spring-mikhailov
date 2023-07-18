package ru.mikhailov.otus.task10.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.mikhailov.otus.task10.domain.dto.AuthorCreateDto;
import ru.mikhailov.otus.task10.domain.dto.AuthorDto;
import ru.mikhailov.otus.task10.service.AuthorService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Authors Controller Tests")
@WebMvcTest(AuthorsController.class)
public class AuthorsControllerTests {

    private final static List<AuthorDto> EXISTING_AUTHORS = List.of(
            new AuthorDto(1L, "Пушкин")
    );

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AuthorService service;

    @Test
    @DisplayName("Should return all authors")
    public void shouldReturnAllAuthors() throws Exception {
        given(service.findAll())
                .willReturn(EXISTING_AUTHORS);

        mvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(EXISTING_AUTHORS)));
    }

    @Test
    @DisplayName("Should create new author")
    public void shouldCreateNewAuthor() throws Exception {

        var authorDto = new AuthorDto(1L, "Пушкин");

        given(service.create(any(AuthorCreateDto.class)))
                .willReturn(authorDto);

        var authorCreateDto = new AuthorCreateDto("Пушкин");

        mvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(authorCreateDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(authorDto)));
    }


}
