package ru.mikhailov.otus.task5.dao;

import ru.mikhailov.otus.task5.domain.model.Genre;

import java.util.List;

public interface GenreDao {

    Genre save(Genre genre);

    Genre findById(Long id);

    List<Genre> findAll();

}
