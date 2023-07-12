package ru.mikhailov.otus.task8.repository;

import ru.mikhailov.otus.task8.domain.model.Comment;

import java.util.List;

public interface CommentRepositoryCustom {

    List<Comment> findAllByBookId(String bookId);

    void deleteAllByBookId(String bookId);

}
