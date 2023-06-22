package ru.mikhailov.otus.task6.service;

import ru.mikhailov.otus.task6.domain.dto.BookDto;
import ru.mikhailov.otus.task6.domain.dto.BookEntityDto;
import ru.mikhailov.otus.task6.domain.model.Book;
import ru.mikhailov.otus.task6.domain.model.Comment;

import java.util.List;

public interface BookService {

    BookEntityDto save(BookDto book);

    void update(BookDto bookDto);

    BookEntityDto findById(Long id);

    List<BookEntityDto> findAll();

    void deleteById(Long id);

}
