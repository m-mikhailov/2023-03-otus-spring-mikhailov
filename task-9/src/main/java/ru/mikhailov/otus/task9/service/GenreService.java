package ru.mikhailov.otus.task9.service;

import ru.mikhailov.otus.task9.domain.dto.GenreCreateDto;
import ru.mikhailov.otus.task9.domain.dto.GenreDto;

import java.util.List;

public interface GenreService {

    GenreDto save(GenreCreateDto genre);

    GenreDto findById(Long id);

    List<GenreDto> findAll();

}
