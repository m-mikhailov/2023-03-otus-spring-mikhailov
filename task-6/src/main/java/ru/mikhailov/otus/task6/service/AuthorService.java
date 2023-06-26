package ru.mikhailov.otus.task6.service;

import ru.mikhailov.otus.task6.domain.dto.AuthorDto;
import ru.mikhailov.otus.task6.domain.model.Author;

import java.util.List;

public interface AuthorService {

    Author create(AuthorDto author);

    Author findById(Long id);

    List<Author> findAll();

}
