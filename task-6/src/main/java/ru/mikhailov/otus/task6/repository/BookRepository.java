package ru.mikhailov.otus.task6.repository;


import ru.mikhailov.otus.task6.domain.model.Book;
import ru.mikhailov.otus.task6.domain.model.Comment;

import java.util.List;

public interface BookRepository {

    Book save(Book book);

    Book findById(Long id);

    List<Book> findAll();

    void update(Book book);

    void deleteById(Long id);

    List<Comment> getBookCommentsById(Long bookId);

    Comment createComment(Comment comment);

}
