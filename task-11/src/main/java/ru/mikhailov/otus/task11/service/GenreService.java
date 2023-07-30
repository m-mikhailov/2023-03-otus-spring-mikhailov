package ru.mikhailov.otus.task11.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.mikhailov.otus.task11.domain.dto.GenreCreateDto;
import ru.mikhailov.otus.task11.domain.dto.GenreDto;

public interface GenreService {

    Mono<GenreDto> save(GenreCreateDto genre);

    Mono<GenreDto> findById(String id);

    Flux<GenreDto> findAll();

}
