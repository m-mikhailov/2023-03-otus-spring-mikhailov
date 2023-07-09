package ru.mikhailov.otus.task8.service;


import ru.mikhailov.otus.task8.domain.dto.AuthorCreateDto;
import ru.mikhailov.otus.task8.domain.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    AuthorDto create(AuthorCreateDto author);

    AuthorDto findById(String id);

    List<AuthorDto> findAll();

}
