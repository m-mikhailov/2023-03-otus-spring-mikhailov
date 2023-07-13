package ru.mikhailov.otus.task9.service;

import ru.mikhailov.otus.task9.domain.dto.BookCreateDto;
import ru.mikhailov.otus.task9.domain.dto.BookDto;
import ru.mikhailov.otus.task9.domain.dto.BookUpdateDto;

import java.util.List;

public interface BookService {

    BookDto save(BookCreateDto book);

    void update(BookUpdateDto bookDto);

    BookDto findById(Long id);

    List<BookDto> findAll();

    void deleteById(Long id);

}
