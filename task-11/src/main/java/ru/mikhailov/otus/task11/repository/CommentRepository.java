package ru.mikhailov.otus.task11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.mikhailov.otus.task11.domain.model.Comment;

@Repository
public interface CommentRepository extends ReactiveMongoRepository<Comment, String>, CommentRepositoryCustom {
}
