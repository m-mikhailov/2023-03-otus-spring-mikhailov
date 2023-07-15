package ru.mikhailov.otus.task9.service;

import ru.mikhailov.otus.task9.domain.dto.AuthorCreateDto;
import ru.mikhailov.otus.task9.domain.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    AuthorDto create(AuthorCreateDto author);

    AuthorDto findById(Long id);

    List<AuthorDto> findAll();

}
