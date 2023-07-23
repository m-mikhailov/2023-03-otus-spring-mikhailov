package ru.mikhailov.otus.task11.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.mikhailov.otus.task11.domain.dto.CommentCreateDto;
import ru.mikhailov.otus.task11.domain.dto.CommentDto;

public interface CommentService {

    Mono<Void> deleteById(String id);

    Mono<CommentDto> add(CommentCreateDto comment);

    Flux<CommentDto> findAllByBookId(String bookId);

}
