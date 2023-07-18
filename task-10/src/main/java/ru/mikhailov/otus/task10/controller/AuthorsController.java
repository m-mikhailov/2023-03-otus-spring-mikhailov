package ru.mikhailov.otus.task10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mikhailov.otus.task10.domain.dto.AuthorCreateDto;
import ru.mikhailov.otus.task10.domain.dto.AuthorDto;
import ru.mikhailov.otus.task10.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorsController {

    private final AuthorService service;

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorDto>> listAuthors() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/authors")
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody AuthorCreateDto author) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(author));
    }

}
