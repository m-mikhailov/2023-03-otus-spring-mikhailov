package ru.mikhailov.otus.task10.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.mikhailov.otus.task10.domain.dto.GenreCreateDto;
import ru.mikhailov.otus.task10.domain.dto.GenreDto;
import ru.mikhailov.otus.task10.service.GenreService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Genres Controller Tests")
@WebMvcTest(GenresController.class)
public class GenresControllerTests {

    private final static List<GenreDto> EXISTING_GENRES = List.of(
            new GenreDto(1L, "Драма")
    );

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private GenreService service;

    @Test
    @DisplayName("Should return all genres")
    public void shouldReturnAllGenres() throws Exception {
        given(service.findAll())
                .willReturn(EXISTING_GENRES);

        mvc.perform(get("/genres"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(EXISTING_GENRES)));
    }

    @Test
    @DisplayName("Should create new genre")
    public void shouldCreateNewGenre() throws Exception {
        var genreDto = new GenreDto(1L, "Драма");

        given(service.save(any(GenreCreateDto.class)))
                .willReturn(genreDto);

        var genreCreateDto = new GenreCreateDto("Драма");

        mvc.perform(post("/genres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(genreCreateDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(genreDto)));
    }
}
