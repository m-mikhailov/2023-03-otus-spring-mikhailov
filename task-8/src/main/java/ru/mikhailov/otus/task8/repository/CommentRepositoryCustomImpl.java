package ru.mikhailov.otus.task8.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.mikhailov.otus.task8.domain.model.Comment;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Comment> findAllByBookId(String bookId) {
        Aggregation aggregation = newAggregation(
                match(Criteria.where("book.id").is(bookId))
        );

        return mongoTemplate.aggregate(aggregation, Comment.class, Comment.class).getMappedResults();
    }

    @Override
    public void deleteAllByBookId(String bookId) {
        var query = Query.query(
                Criteria.where("book.id").is(bookId)
        );

        mongoTemplate.remove(query, Comment.class);
    }
}
