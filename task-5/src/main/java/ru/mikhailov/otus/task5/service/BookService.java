package ru.mikhailov.otus.task5.service;

import ru.mikhailov.otus.task5.domain.dto.BookDto;
import ru.mikhailov.otus.task5.domain.model.Book;

import java.util.List;

public interface BookService {

    Book save(BookDto book);

    void updateById(Long id, BookDto bookDto);

    Book findById(Long id);

    List<Book> findAll();

    void deleteById(Long id);

}
