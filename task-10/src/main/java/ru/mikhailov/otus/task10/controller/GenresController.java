package ru.mikhailov.otus.task10.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<GenreDto>> listGenres() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/genres")
    public ResponseEntity<GenreDto> addGenre(@RequestBody GenreCreateDto genre) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(genre));
    }

}
