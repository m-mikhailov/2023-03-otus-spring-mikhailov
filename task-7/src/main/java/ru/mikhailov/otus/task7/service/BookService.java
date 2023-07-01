package ru.mikhailov.otus.task7.service;


import ru.mikhailov.otus.task7.domain.dto.BookDto;
import ru.mikhailov.otus.task7.domain.dto.BookEntityDto;

import java.util.List;

public interface BookService {

    BookEntityDto save(BookDto book);

    void update(BookDto bookDto);

    BookEntityDto findById(Long id);

    List<BookEntityDto> findAll();

    void deleteById(Long id);

}
