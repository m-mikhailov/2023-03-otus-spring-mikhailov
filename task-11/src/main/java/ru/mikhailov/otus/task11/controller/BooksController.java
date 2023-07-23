package ru.mikhailov.otus.task11.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.mikhailov.otus.task11.domain.dto.BookCreateDto;
import ru.mikhailov.otus.task11.domain.dto.BookDto;
import ru.mikhailov.otus.task11.domain.dto.BookUpdateDto;
import ru.mikhailov.otus.task11.service.BookService;

@RestController
@RequiredArgsConstructor
public class BooksController {

    private final BookService service;

    @GetMapping( "/books")
    public Flux<BookDto> listBooks() {
        return service.findAll();
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BookDto> addBook(@Valid @RequestBody BookCreateDto book) {
        return service.save(book);
    }

    @GetMapping("/books/{id}")
    public Mono<BookDto> getBook(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @PutMapping("/books")
    public Mono<Void> updateBook(@RequestBody BookUpdateDto book) {
        return service.update(book);
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteBook(@PathVariable("id") String id) {
        return service.deleteById(id);
    }
}
