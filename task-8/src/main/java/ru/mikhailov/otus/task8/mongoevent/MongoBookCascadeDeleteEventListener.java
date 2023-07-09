package ru.mikhailov.otus.task8.mongoevent;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.mikhailov.otus.task8.domain.model.Book;
import ru.mikhailov.otus.task8.repository.CommentRepository;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeDeleteEventListener extends AbstractMongoEventListener<Book> {

    private final CommentRepository repository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Book> event) {
        super.onAfterDelete(event);
        var source = event.getSource();
        var id = source.get("_id").toString();
        var comments = repository.findAllByBookId(id);
        repository.deleteAll(comments);
    }

}
