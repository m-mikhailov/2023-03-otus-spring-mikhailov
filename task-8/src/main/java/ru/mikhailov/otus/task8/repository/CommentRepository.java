package ru.mikhailov.otus.task8.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.mikhailov.otus.task8.domain.model.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String>, CommentRepositoryCustom {
}
