package ru.mikhailov.otus.task6.repository;

import ru.mikhailov.otus.task6.domain.model.Comment;

import java.util.List;

public interface CommentRepository {

    void update(Comment comment);

    void deleteById(Long id);

    Comment save(Comment comment);

    List<Comment> findAllByBookId(Long bookId);

}
