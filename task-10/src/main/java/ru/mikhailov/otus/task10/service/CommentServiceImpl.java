package ru.mikhailov.otus.task10.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailov.otus.task10.domain.dto.CommentCreateDto;
import ru.mikhailov.otus.task10.domain.dto.CommentDto;
import ru.mikhailov.otus.task10.domain.error.EntityNotFoundException;
import ru.mikhailov.otus.task10.domain.model.Comment;
import ru.mikhailov.otus.task10.repository.BookRepository;
import ru.mikhailov.otus.task10.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Override
    @Transactional
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CommentDto add(CommentCreateDto comment) {
        return bookRepository.findById(comment.bookId())
                .map(book -> new Comment(comment.text(), book))
                .map(commentRepository::save)
                .map(CommentDto::new)
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.BOOK_MESSAGE_FORMAT, comment.bookId()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> findAllByBookId(Long bookId) {
        return bookRepository.findById(bookId)
                .map(book -> commentRepository.findAllByBookId(bookId))
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.BOOK_MESSAGE_FORMAT, bookId))
                .stream()
                .map(CommentDto::new)
                .toList();
    }

}
