package ru.mikhailov.otus.task6.service;

import ru.mikhailov.otus.task6.domain.dto.CommentDto;
import ru.mikhailov.otus.task6.domain.model.Comment;

import java.util.List;

public interface CommentService {

    void update(Comment comment);

    void deleteById(Long id);

    Comment add(CommentDto comment);

    List<Comment> findAllByBookId(Long bookId);

}
