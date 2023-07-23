package ru.mikhailov.otus.task10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public List<AuthorDto> listAuthors() {
        return service.findAll();
    }

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDto addAuthor(@RequestBody AuthorCreateDto author) {
        return service.create(author);
    }

}
