package ru.mikhailov.otus.task11.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.mikhailov.otus.task11.domain.dto.GenreCreateDto;
import ru.mikhailov.otus.task11.domain.dto.GenreDto;
import ru.mikhailov.otus.task11.service.GenreService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@DisplayName("Genres Controller Tests")
@WebFluxTest(GenresController.class)
public class GenresControllerTests {

    private final static GenreDto EXISTING_GENRE = new GenreDto("1", "Драма");

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private GenreService service;

    @Test
    @DisplayName("Should return all genres")
    public void shouldReturnAllGenres() throws Exception {
        given(service.findAll())
                .willReturn(Flux.just(EXISTING_GENRE));

        webClient.get().uri("/genres")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json(mapper.writeValueAsString(List.of(EXISTING_GENRE)));
    }

    @Test
    @DisplayName("Should create new genre")
    public void shouldCreateNewGenre() throws Exception {
        var genreDto = new GenreDto("1", "Драма");

        given(service.save(any(GenreCreateDto.class)))
                .willReturn(Mono.just(genreDto));

        var genreCreateDto = new GenreCreateDto("Драма");

        webClient.post().uri("/genres")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(genreCreateDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().json(mapper.writeValueAsString(genreDto));
    }
}
