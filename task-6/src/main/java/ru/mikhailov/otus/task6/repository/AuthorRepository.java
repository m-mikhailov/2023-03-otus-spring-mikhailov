package ru.mikhailov.otus.task6.repository;


import ru.mikhailov.otus.task6.domain.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Author save(Author author);

    Optional<Author> findById(Long id);

    List<Author> findAll();

}
