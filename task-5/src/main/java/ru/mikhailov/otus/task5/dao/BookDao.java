package ru.mikhailov.otus.task5.dao;

import ru.mikhailov.otus.task5.domain.model.Book;

import java.util.List;

public interface BookDao {

    Book save(Book book);

    Book findById(Long id);

    List<Book> findAll();

    void update(Book book);

    void deleteById(Long id);

}
