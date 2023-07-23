package ru.mikhailov.otus.task11.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.mikhailov.otus.task11.domain.dto.CommentCreateDto;
import ru.mikhailov.otus.task11.domain.dto.CommentDto;
import ru.mikhailov.otus.task11.domain.error.EntityNotFoundException;
import ru.mikhailov.otus.task11.domain.model.Comment;
import ru.mikhailov.otus.task11.repository.BookRepository;
import ru.mikhailov.otus.task11.repository.CommentRepository;

import static ru.mikhailov.otus.task11.domain.error.EntityNotFoundException.BOOK_MESSAGE_FORMAT;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Override
    public Mono<Void> deleteById(String id) {
        return commentRepository.deleteById(id).then();
    }

    @Override
    public Mono<CommentDto> add(CommentCreateDto comment) {
        return bookRepository.findById(comment.bookId())
                .switchIfEmpty(Mono.error(new EntityNotFoundException(BOOK_MESSAGE_FORMAT, comment.bookId())))
                .map(book -> new Comment(comment.text(), book))
                .flatMap(commentRepository::save)
                .map(CommentDto::new);
    }

    @Override
    public Flux<CommentDto> findAllByBookId(String bookId) {
        return bookRepository.findById(bookId)
                .switchIfEmpty(Mono.error(new EntityNotFoundException(BOOK_MESSAGE_FORMAT, bookId)))
                .flatMapMany(book -> commentRepository.findAllByBookId(bookId))
                .map(CommentDto::new);
    }

}
