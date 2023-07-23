package ru.mikhailov.otus.task10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.mikhailov.otus.task10.domain.dto.GenreCreateDto;
import ru.mikhailov.otus.task10.domain.dto.GenreDto;
import ru.mikhailov.otus.task10.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenresController {

    private final GenreService service;

    @GetMapping("/genres")
    public List<GenreDto> listGenres() {
        return service.findAll();
    }

    @PostMapping("/genres")
    @ResponseStatus(HttpStatus.CREATED)
    public GenreDto addGenre(@RequestBody GenreCreateDto genre) {
        return service.save(genre);
    }

}
