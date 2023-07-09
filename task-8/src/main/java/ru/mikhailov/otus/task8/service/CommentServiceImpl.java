package ru.mikhailov.otus.task8.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task8.domain.dto.CommentCreateDto;
import ru.mikhailov.otus.task8.domain.dto.CommentDto;
import ru.mikhailov.otus.task8.domain.dto.CommentUpdateDto;
import ru.mikhailov.otus.task8.domain.error.EntityNotFoundException;
import ru.mikhailov.otus.task8.domain.model.Comment;
import ru.mikhailov.otus.task8.repository.BookRepository;
import ru.mikhailov.otus.task8.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    @Override
    public void update(CommentUpdateDto commentDto) {
        var comment = commentRepository.findById(commentDto.id())
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.COMMENT_MESSAGE_FORMAT, commentDto.id()));

        comment.setText(comment.getText());

        commentRepository.save(comment);
    }

    @Override
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }

    @Override
    public CommentDto add(CommentCreateDto comment) {
        return bookRepository.findById(comment.bookId())
                .map(book -> new Comment(comment.text(), book))
                .map(commentRepository::save)
                .map(CommentDto::new)
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.BOOK_MESSAGE_FORMAT, comment.bookId()));
    }

    @Override
    public List<CommentDto> findAllByBookId(String bookId) {
        return bookRepository.findById(bookId)
                .map(book -> commentRepository.findAllByBookId(bookId))
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.BOOK_MESSAGE_FORMAT, bookId))
                .stream()
                .map(CommentDto::new)
                .toList();
    }

}
