package ru.mikhailov.otus.task6.repository;


import ru.mikhailov.otus.task6.domain.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    Genre save(Genre genre);

    Optional<Genre> findById(Long id);

    List<Genre> findAll();

}
