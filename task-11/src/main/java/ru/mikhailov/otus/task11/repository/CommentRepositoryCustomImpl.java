package ru.mikhailov.otus.task11.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.mikhailov.otus.task11.domain.model.Comment;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<Comment> findAllByBookId(String bookId) {
        Aggregation aggregation = newAggregation(
                match(Criteria.where("book.id").is(bookId))
        );

        return mongoTemplate.aggregate(aggregation, Comment.class, Comment.class);
    }

    @Override
    public Mono<Void> deleteAllByBookId(String bookId) {
        var query = Query.query(
                Criteria.where("book.id").is(bookId)
        );

        return mongoTemplate.findAllAndRemove(query, Comment.class).then();
    }
}
