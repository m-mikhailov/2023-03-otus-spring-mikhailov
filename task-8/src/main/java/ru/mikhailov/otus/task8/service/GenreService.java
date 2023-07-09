package ru.mikhailov.otus.task8.service;


import ru.mikhailov.otus.task8.domain.dto.GenreCreateDto;
import ru.mikhailov.otus.task8.domain.dto.GenreDto;

import java.util.List;

public interface GenreService {

    GenreDto save(GenreCreateDto genre);

    GenreDto findById(String id);

    List<GenreDto> findAll();

}
