package ru.mikhailov.otus.task8.service;


import ru.mikhailov.otus.task8.domain.dto.BookCreateDto;
import ru.mikhailov.otus.task8.domain.dto.BookDto;
import ru.mikhailov.otus.task8.domain.dto.BookUpdateDto;

import java.util.List;

public interface BookService {

    BookDto save(BookCreateDto book);

    void update(BookUpdateDto bookDto);

    BookDto findById(String id);

    List<BookDto> findAll();

    void deleteById(String id);

}
