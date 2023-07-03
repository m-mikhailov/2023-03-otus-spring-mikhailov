package ru.mikhailov.otus.task7.service;

import ru.mikhailov.otus.task7.domain.dto.AuthorCreateDto;
import ru.mikhailov.otus.task7.domain.dto.AuthorDto;
import ru.mikhailov.otus.task7.domain.model.Author;

import java.util.List;

public interface AuthorService {

    AuthorDto create(AuthorCreateDto author);

    AuthorDto findById(Long id);

    List<AuthorDto> findAll();

}
