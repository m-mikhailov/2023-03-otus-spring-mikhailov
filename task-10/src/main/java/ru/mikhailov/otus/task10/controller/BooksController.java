package ru.mikhailov.otus.task10.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.mikhailov.otus.task10.domain.dto.BookCreateDto;
import ru.mikhailov.otus.task10.domain.dto.BookDto;
import ru.mikhailov.otus.task10.domain.dto.BookUpdateDto;
import ru.mikhailov.otus.task10.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BooksController {

    private final BookService service;

    @GetMapping("/books")
    public List<BookDto> listBooks() {
        return service.findAll();
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto addBook(@Valid @RequestBody BookCreateDto book) {
        return service.save(book);
    }

    @GetMapping("/books/{id}")
    public BookDto getBook(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PutMapping("/books")
    public void updateBook(@RequestBody BookUpdateDto book) {
        service.update(book);
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("id") Long id) {
        service.deleteById(id);
    }
}
