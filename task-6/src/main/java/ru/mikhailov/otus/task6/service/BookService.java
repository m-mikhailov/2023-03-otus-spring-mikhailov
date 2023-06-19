package ru.mikhailov.otus.task6.service;

import ru.mikhailov.otus.task6.domain.dto.BookDto;
import ru.mikhailov.otus.task6.domain.model.Book;
import ru.mikhailov.otus.task6.domain.model.Comment;

import java.util.List;

public interface BookService {

    Book save(BookDto book);

    void updateById(Long id, BookDto bookDto);

    Book findById(Long id);

    List<Book> findAll();

    void deleteById(Long id);

    List<Comment> getBookComments(Long id);

}
