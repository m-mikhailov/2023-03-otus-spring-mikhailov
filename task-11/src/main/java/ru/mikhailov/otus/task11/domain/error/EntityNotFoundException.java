package ru.mikhailov.otus.task11.domain.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityNotFoundException extends ResponseStatusException {

    public final static String AUTHOR_MESSAGE_FORMAT = "Author with id %s not found";

    public final static String BOOK_MESSAGE_FORMAT = "Book with id %s not found";

    public final static String GENRE_MESSAGE_FORMAT = "Genre with id %s not found";

    public EntityNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public EntityNotFoundException(String messageFormat, String id) {
        this(messageFormat.formatted(id));
    }
}
