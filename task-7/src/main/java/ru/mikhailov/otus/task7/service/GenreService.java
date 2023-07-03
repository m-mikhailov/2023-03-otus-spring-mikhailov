package ru.mikhailov.otus.task7.service;

import ru.mikhailov.otus.task7.domain.dto.GenreCreateDto;
import ru.mikhailov.otus.task7.domain.dto.GenreDto;

import java.util.List;

public interface GenreService {

    GenreDto save(GenreCreateDto genre);

    GenreDto findById(Long id);

    List<GenreDto> findAll();

}
