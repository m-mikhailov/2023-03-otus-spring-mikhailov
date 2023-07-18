package ru.mikhailov.otus.task10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<BookDto>> listBooks() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/books")
    public ResponseEntity<BookDto> addBook(@RequestBody BookCreateDto book) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(book));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/books")
    public ResponseEntity<?> updateBook(@RequestBody BookUpdateDto book) {
        service.update(book);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
