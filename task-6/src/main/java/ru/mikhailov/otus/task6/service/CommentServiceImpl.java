package ru.mikhailov.otus.task6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mikhailov.otus.task6.domain.dto.CommentDto;
import ru.mikhailov.otus.task6.domain.model.Book;
import ru.mikhailov.otus.task6.domain.model.Comment;
import ru.mikhailov.otus.task6.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    @Override
    @Transactional
    public void update(Comment comment) {
        repository.update(comment);
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
