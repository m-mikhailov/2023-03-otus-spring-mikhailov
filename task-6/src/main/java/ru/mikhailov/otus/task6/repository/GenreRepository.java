package ru.mikhailov.otus.task6.repository;


import ru.mikhailov.otus.task6.domain.model.Genre;

import java.util.List;

public interface GenreRepository {

    Genre save(Genre genre);

    Genre findById(Long id);

    List<Genre> findAll();

}
