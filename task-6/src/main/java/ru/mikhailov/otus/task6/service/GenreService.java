package ru.mikhailov.otus.task6.service;

import ru.mikhailov.otus.task6.domain.dto.GenreDto;
import ru.mikhailov.otus.task6.domain.model.Genre;

import java.util.List;

public interface GenreService {

    Genre save(GenreDto genre);

    Genre findById(Long id);

    List<Genre> findAll();

}
