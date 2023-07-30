package ru.mikhailov.otus.task11.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.mikhailov.otus.task11.domain.dto.GenreCreateDto;
import ru.mikhailov.otus.task11.domain.dto.GenreDto;
import ru.mikhailov.otus.task11.service.GenreService;

@RestController
@RequiredArgsConstructor
public class GenresController {

    private final GenreService service;

    @GetMapping("/genres")
    public Flux<GenreDto> listGenres() {
        return service.findAll();
    }

    @PostMapping("/genres")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<GenreDto> addGenre(@RequestBody GenreCreateDto genre) {
        return service.save(genre);
    }

}
