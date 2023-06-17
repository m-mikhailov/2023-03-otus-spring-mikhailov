package ru.mikhailov.otus.task6.repository;


import ru.mikhailov.otus.task6.domain.model.Author;

import java.util.List;

public interface AuthorRepository {

    Author save(Author author);

    Author findById(Long id);

    List<Author> findAll();

}
