package ru.mikhailov.otus.task11.repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.mikhailov.otus.task11.domain.model.Comment;

public interface CommentRepositoryCustom {

    Flux<Comment> findAllByBookId(String bookId);

    Mono<Void> deleteAllByBookId(String bookId);

}
