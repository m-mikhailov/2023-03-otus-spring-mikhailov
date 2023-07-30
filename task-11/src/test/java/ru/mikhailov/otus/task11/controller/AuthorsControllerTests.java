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
import ru.mikhailov.otus.task11.domain.dto.AuthorCreateDto;
import ru.mikhailov.otus.task11.domain.dto.AuthorDto;
import ru.mikhailov.otus.task11.service.AuthorService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@DisplayName("Authors Controller Tests")
@WebFluxTest(AuthorsController.class)
public class AuthorsControllerTests {

    private final static AuthorDto EXISTING_AUTHOR = new AuthorDto("1", "Пушкин");

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AuthorService service;

    @Test
    @DisplayName("Should return all authors")
    public void shouldReturnAllAuthors() throws Exception {
        given(service.findAll())
                .willReturn(Flux.just(EXISTING_AUTHOR));

        webClient.get().uri("/authors")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json(mapper.writeValueAsString(List.of(EXISTING_AUTHOR)));
    }

    @Test
    @DisplayName("Should create new author")
    public void shouldCreateNewAuthor() throws Exception {

        var authorDto = new AuthorDto("1", "Пушкин");

        given(service.create(any(AuthorCreateDto.class)))
                .willReturn(Mono.just(authorDto));

        var authorCreateDto = new AuthorCreateDto("Пушкин");

        webClient.post().uri("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(authorCreateDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .json(mapper.writeValueAsString(authorDto));
    }


}
