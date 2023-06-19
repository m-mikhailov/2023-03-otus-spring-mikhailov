package ru.mikhailov.otus.task6.repository;


import ru.mikhailov.otus.task6.domain.model.Book;
import ru.mikhailov.otus.task6.domain.model.Comment;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);

    Optional<Book> findById(Long id);

    List<Book> findAll();

    void update(Book book);

    void deleteById(Long id);

    List<Comment> getBookCommentsById(Long bookId);

}
