package ru.mikhailov.otus.task10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mikhailov.otus.task10.domain.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}