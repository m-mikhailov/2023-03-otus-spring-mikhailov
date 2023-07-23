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
import ru.mikhailov.otus.task11.domain.dto.CommentCreateDto;
import ru.mikhailov.otus.task11.domain.dto.CommentDto;
import ru.mikhailov.otus.task11.service.CommentService;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@DisplayName("Comments Controller Tests")
@WebFluxTest(CommentsController.class)
public class CommentsControllerTests {

    private final static List<CommentDto> EXISTING_COMMENTS = List.of(
            new CommentDto("1", "Супер!"),
            new CommentDto("2", "Класс!")
    );

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CommentService service;

    @Test
    @DisplayName("Should return all comments by bookId")
    public void shouldReturnAllBookComments() throws Exception {
        given(service.findAllByBookId(anyString()))
                .willReturn(Flux.fromIterable(EXISTING_COMMENTS));

        webClient.get().uri(builder -> builder.path("/comments")
                        .queryParam("bookId", "1")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(mapper.writeValueAsString(EXISTING_COMMENTS));
    }

    @Test
    @DisplayName("Should add new comment")
    public void shouldAddNewComment() throws Exception {

        var commentCreateDto = new CommentCreateDto("Круто", "1");
        var commentDto = new CommentDto("3", "Круто");

        given(service.add(any(CommentCreateDto.class)))
                .willReturn(Mono.just(commentDto));

        webClient.post().uri(builder -> builder.path("/comments")
                        .queryParam("bookId", "1")
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(commentCreateDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().json(mapper.writeValueAsString(commentDto));
    }

    @Test
    @DisplayName("Should delete comment")
    public void shouldDeleteComment() throws Exception {
        webClient.delete().uri("/comments/{id}", "1")
                .exchange()
                .expectStatus().isNoContent();

        verify(service).deleteById(eq("1"));
    }

}
