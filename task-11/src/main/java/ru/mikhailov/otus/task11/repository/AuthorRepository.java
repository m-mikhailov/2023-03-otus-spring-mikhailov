package ru.mikhailov.otus.task11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.mikhailov.otus.task11.domain.model.Author;

@Repository
public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
}
