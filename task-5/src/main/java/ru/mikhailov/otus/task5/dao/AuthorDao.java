package ru.mikhailov.otus.task5.dao;

import ru.mikhailov.otus.task5.domain.model.Author;

import java.util.List;

public interface AuthorDao {

    Author save(Author author);

    Author findById(Long id);

    List<Author> findAll();

}
