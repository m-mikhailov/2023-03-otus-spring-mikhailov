package ru.mikhailov.otus.task8.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.mikhailov.otus.task8.domain.model.Author;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {
}
