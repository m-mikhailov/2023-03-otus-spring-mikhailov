package ru.mikhailov.otus.task7.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailov.otus.task7.domain.dto.CommentCreateDto;
import ru.mikhailov.otus.task7.domain.dto.CommentDto;
import ru.mikhailov.otus.task7.domain.dto.CommentUpdateDto;
import ru.mikhailov.otus.task7.domain.error.BookNotFoundException;
import ru.mikhailov.otus.task7.domain.error.CommentNotFoundException;
import ru.mikhailov.otus.task7.domain.model.Comment;
import ru.mikhailov.otus.task7.repository.BookRepository;
import ru.mikhailov.otus.task7.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Override
    @Transactional
    public void update(CommentUpdateDto commentDto) {
        var comment = commentRepository.findById(commentDto.id())
                .orElseThrow(() -> new CommentNotFoundException(commentDto.id()));

        comment.setText(comment.getText());

        commentRepository.save(comment);
    }

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
                .orElseThrow(() -> new BookNotFoundException(comment.bookId()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> findAllByBookId(Long bookId) {
        return bookRepository.findById(bookId)
                .map(book -> commentRepository.findAllByBookId(bookId))
                .orElseThrow(() -> new BookNotFoundException(bookId))
                .stream()
                .map(CommentDto::new)
                .toList();
    }

}
