package ru.mikhailov.otus.task9.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.mikhailov.otus.task9.domain.dto.GenreCreateDto;
import ru.mikhailov.otus.task9.domain.dto.GenreDto;
import ru.mikhailov.otus.task9.service.GenreService;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Genres Controller Tests")
@WebMvcTest(GenresController.class)
public class GenresControllerTests {

    private final static List<GenreDto> EXISTING_GENRES = List.of(
            new GenreDto(1L, "Драма")
    );

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GenreService service;


    @Test
    @DisplayName("Should return add view")
    public void shouldReturnAddView() throws Exception {
        mvc.perform(get("/newgenre"))
                .andExpect(status().isOk())
                .andExpect(view().name("genres/add"));
    }

    @Test
    @DisplayName("Should return model genres and list view")
    public void shouldReturnGenresAndListView() throws Exception {
        given(service.findAll())
                .willReturn(EXISTING_GENRES);

        mvc.perform(get("/genres"))
                .andExpect(status().isOk())
                .andExpect(view().name("genres/list"))
                .andExpect(model().attribute("genres", EXISTING_GENRES));
    }

    @Test
    @DisplayName("Should create genre and return redirect")
    public void shouldCreateGenreAndReturnRedirect() throws Exception {
        mvc.perform(post("/genres")
                        .param("name", "Драма"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/genres"));

        verify(service).save(eq(new GenreCreateDto("Драма")));
    }
}
