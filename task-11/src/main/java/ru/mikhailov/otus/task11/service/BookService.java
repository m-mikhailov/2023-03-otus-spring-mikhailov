package ru.mikhailov.otus.task11.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.mikhailov.otus.task11.domain.dto.BookCreateDto;
import ru.mikhailov.otus.task11.domain.dto.BookDto;
import ru.mikhailov.otus.task11.domain.dto.BookUpdateDto;

public interface BookService {

    Mono<BookDto> save(BookCreateDto book);

    Mono<Void> update(BookUpdateDto bookDto);

    Mono<BookDto> findById(String id);

    Flux<BookDto> findAll();

    Mono<Void> deleteById(String id);

}
