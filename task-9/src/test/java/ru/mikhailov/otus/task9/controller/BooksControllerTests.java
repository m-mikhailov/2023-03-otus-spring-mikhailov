package ru.mikhailov.otus.task9.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.mikhailov.otus.task9.domain.dto.BookCreateDto;
import ru.mikhailov.otus.task9.domain.dto.BookDto;
import ru.mikhailov.otus.task9.domain.dto.BookUpdateDto;
import ru.mikhailov.otus.task9.domain.model.Author;
import ru.mikhailov.otus.task9.domain.model.Genre;
import ru.mikhailov.otus.task9.service.BookService;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @MockBean
    private BookService service;

    @Test
    @DisplayName("Should return add view")
    public void shouldReturnAddView() throws Exception {
        mvc.perform(get("/newbook"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/add"));
    }

    @Test
    @DisplayName("Should return model books and list view")
    public void shouldReturnBooksAndListView() throws Exception {
        given(service.findAll())
                .willReturn(EXISTING_BOOKS);

        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/list"))
                .andExpect(model().attribute("books", EXISTING_BOOKS));
    }

    @Test
    @DisplayName("Should return model book and edit view")
    public void shouldReturnBookAndEditView() throws Exception {
        given(service.findById(anyLong()))
                .willReturn(EXISTING_BOOK);

        mvc.perform(get("/books/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/edit"))
                .andExpect(model().attribute("book", EXISTING_BOOK));
    }

    @Test
    @DisplayName("Should update book and return redirect")
    public void shouldUpdateBookAndReturnRedirect() throws Exception {

        mvc.perform(post("/books/{id}", "1")
                        .param("id", "1")
                        .param("name", "Дочка")
                        .param("authorId", "1")
                        .param("genreId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/books"));

        verify(service).update(new BookUpdateDto(1L, "Дочка", 1L, 1L));
    }

    @Test
    @DisplayName("Should create book and return redirect")
    public void shouldCreateBookAndReturnRedirect() throws Exception {
        mvc.perform(post("/books")
                        .param("name", "Капитанская дочка")
                        .param("authorId", "1")
                        .param("genreId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/books"));

        verify(service).save(eq(new BookCreateDto("Капитанская дочка", 1L, 1L)));
    }

    @Test
    @DisplayName("Should delete book and return redirect")
    public void shouldDeleteBookAndReturnRedirect() throws Exception {
        mvc.perform(get("/books/delete/{id}", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/books"));

        verify(service).deleteById(eq(1L));
    }



}
