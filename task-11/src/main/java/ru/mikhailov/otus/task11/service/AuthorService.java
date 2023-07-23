package ru.mikhailov.otus.task11.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.mikhailov.otus.task11.domain.dto.AuthorCreateDto;
import ru.mikhailov.otus.task11.domain.dto.AuthorDto;

public interface AuthorService {

    Mono<AuthorDto> create(AuthorCreateDto author);

    Mono<AuthorDto> findById(String id);

    Flux<AuthorDto> findAll();

}
