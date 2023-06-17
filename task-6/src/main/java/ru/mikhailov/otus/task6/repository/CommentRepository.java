package ru.mikhailov.otus.task6.repository;

import ru.mikhailov.otus.task6.domain.model.Comment;

public interface CommentRepository {

    void update(Comment comment);

    void deleteById(Long id);

}
