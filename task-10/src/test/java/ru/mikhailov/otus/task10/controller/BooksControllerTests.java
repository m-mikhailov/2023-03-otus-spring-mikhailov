package ru.mikhailov.otus.task10.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.mikhailov.otus.task10.domain.dto.BookCreateDto;
import ru.mikhailov.otus.task10.domain.dto.BookDto;
import ru.mikhailov.otus.task10.domain.dto.BookUpdateDto;
import ru.mikhailov.otus.task10.domain.model.Author;
import ru.mikhailov.otus.task10.domain.model.Genre;
import ru.mikhailov.otus.task10.service.BookService;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Books Controller Tests")
@WebMvcTest(BooksController.class)
public class BooksControllerTests {

    private final static BookDto EXISTING_BOOK =
            new BookDto(1L, "Капитанская дочка", new Author(1L, "Пушкин"), new Genre(1L, "Драма"));
    private final static List<BookDto> EXISTING_BOOKS = List.of(
            EXISTING_BOOK
    );

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BookService service;

    @Test
    @DisplayName("Should return all books")
    public void shouldReturnAllBooks() throws Exception {
        given(service.findAll())
                .willReturn(EXISTING_BOOKS);

        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(EXISTING_BOOKS)));
    }

    @Test
    @DisplayName("Should return book by id")
    public void shouldReturnBookById() throws Exception {
        given(service.findById(anyLong()))
                .willReturn(EXISTING_BOOK);

        mvc.perform(get("/books/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(EXISTING_BOOK)));
    }

    @Test
    @DisplayName("Should update book")
    public void shouldUpdateBook() throws Exception {

        var bookUpdateDto = new BookUpdateDto(1L, "Другое название", 1L, 1L);

        mvc.perform(put("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(bookUpdateDto)))
                .andExpect(status().isOk());

        verify(service).update(eq(bookUpdateDto));
    }

    @Test
    @DisplayName("Should create new book")
    public void shouldCreateNewBook() throws Exception {

        var bookCreateDto = new BookCreateDto("Капитанская дочка", 1L, 1L);

        given(service.save(any(BookCreateDto.class)))
                .willReturn(EXISTING_BOOK);

        mvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(bookCreateDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(EXISTING_BOOK)));
    }

    @Test
    @DisplayName("Empty book name, should return bad request status")
    public void shouldReturnBadRequestStatus_emptyBookName() throws Exception {
        var bookCreateDto = new BookCreateDto("", 1L, 1L);

        mvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(bookCreateDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("AuthorId is null, should return bad request status")
    public void shouldReturnBadRequestStatus_authorIdIsNull() throws Exception {
        var bookCreateDto = new BookCreateDto("Капитанская дочка", null, 1L);

        mvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(bookCreateDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should delete book")
    public void shouldDeleteBook() throws Exception {
        mvc.perform(delete("/books/{id}", "1"))
                .andExpect(status().isNoContent());

        verify(service).deleteById(eq(1L));
    }


}
