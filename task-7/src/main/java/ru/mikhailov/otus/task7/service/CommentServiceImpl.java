package ru.mikhailov.otus.task7.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailov.otus.task7.domain.dto.CommentDto;
import ru.mikhailov.otus.task7.domain.error.EntityNotFoundException;
import ru.mikhailov.otus.task7.domain.model.Book;
import ru.mikhailov.otus.task7.domain.model.Comment;
import ru.mikhailov.otus.task7.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    @Override
    @Transactional
    public void update(Comment comment) {
        var existingComment = repository.findById(comment.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Comment with id %s not found".formatted(comment.getId())));
        var newComment = new Comment(
                existingComment.getId(),
                comment.getText(),
                existingComment.getBook()
        );

        repository.save(newComment);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Comment add(CommentDto comment) {
        var newComment = new Comment(null, comment.text(), new Book(comment.bookId(), null, null, null));
        return repository.save(newComment);
    }

    @Override
    @Transactional
    public List<Comment> findAllByBookId(Long bookId) {
        return repository.findAllByBookId(bookId);
    }

}
