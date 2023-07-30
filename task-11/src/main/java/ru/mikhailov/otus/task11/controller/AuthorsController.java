package ru.mikhailov.otus.task11.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.mikhailov.otus.task11.domain.dto.AuthorCreateDto;
import ru.mikhailov.otus.task11.domain.dto.AuthorDto;
import ru.mikhailov.otus.task11.service.AuthorService;

@RestController
@RequiredArgsConstructor
public class AuthorsController {

    private final AuthorService service;

    @GetMapping( "/authors")
    public Flux<AuthorDto> listAuthors() {
        return service.findAll()
                .log();
    }

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AuthorDto> addAuthor(@RequestBody AuthorCreateDto author) {
        return service.create(author);
    }

}
