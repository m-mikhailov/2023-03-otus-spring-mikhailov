package ru.mikhailov.otus.task11.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.mikhailov.otus.task11.domain.dto.BookCreateDto;
import ru.mikhailov.otus.task11.domain.dto.BookDto;
import ru.mikhailov.otus.task11.domain.dto.BookUpdateDto;
import ru.mikhailov.otus.task11.domain.model.Author;
import ru.mikhailov.otus.task11.domain.model.Genre;
import ru.mikhailov.otus.task11.service.BookService;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@DisplayName("Books Controller Tests")
@WebFluxTest(BooksController.class)
public class BooksControllerTests {

    private final static BookDto EXISTING_BOOK =
            new BookDto("1", "Капитанская дочка", new Author("1", "Пушкин"), new Genre("1", "Драма"));
    private final static List<BookDto> EXISTING_BOOKS = List.of(
            EXISTING_BOOK
    );

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BookService service;

    @Test
    @DisplayName("Should return all books")
    public void shouldReturnAllBooks() throws Exception {
        given(service.findAll())
                .willReturn(Flux.fromIterable(EXISTING_BOOKS));

        webClient.get().uri("/books")
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(mapper.writeValueAsString(EXISTING_BOOKS));
    }

    @Test
    @DisplayName("Should return book by id")
    public void shouldReturnBookById() throws Exception {
        given(service.findById(anyString()))
                .willReturn(Mono.just(EXISTING_BOOK));

        webClient.get().uri("/books/{id}", "1")
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(mapper.writeValueAsString(EXISTING_BOOK));
    }

    @Test
    @DisplayName("Should update book")
    public void shouldUpdateBook() throws Exception {

        var bookUpdateDto = new BookUpdateDto("1", "Другое название", "1", "1");

        webClient.put().uri("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(bookUpdateDto)
                .exchange()
                .expectStatus().isOk();

        verify(service).update(eq(bookUpdateDto));
    }

    @Test
    @DisplayName("Should create new book")
    public void shouldCreateNewBook() throws Exception {

        var bookCreateDto = new BookCreateDto("Капитанская дочка", "1", "1");

        given(service.save(any(BookCreateDto.class)))
                .willReturn(Mono.just(EXISTING_BOOK));

        webClient.post().uri("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(bookCreateDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().json(mapper.writeValueAsString(EXISTING_BOOK));
    }

    @Test
    @DisplayName("Empty book name, should return bad request status")
    public void shouldReturnBadRequestStatus_emptyBookName() throws Exception {
        var bookCreateDto = new BookCreateDto("", "1", "1");

        webClient.post().uri("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(mapper.writeValueAsString(bookCreateDto))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("AuthorId is null, should return bad request status")
    public void shouldReturnBadRequestStatus_authorIdIsNull() throws Exception {
        var bookCreateDto = new BookCreateDto("Капитанская дочка", null, "1");

        webClient.post().uri("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(mapper.writeValueAsString(bookCreateDto))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("Should delete book")
    public void shouldDeleteBook() {
        webClient.delete().uri("/books/{id}", "1")
                .exchange()
                .expectStatus().isNoContent();

        verify(service).deleteById(eq("1"));
    }


}
