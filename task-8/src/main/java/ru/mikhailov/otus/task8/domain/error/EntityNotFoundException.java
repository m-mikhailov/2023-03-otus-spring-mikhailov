package ru.mikhailov.otus.task8.domain.error;

public class EntityNotFoundException extends RuntimeException {

    public final static String AUTHOR_MESSAGE_FORMAT = "Author with id %s not found";

    public final static String BOOK_MESSAGE_FORMAT = "Book with id %s not found";

    public final static String COMMENT_MESSAGE_FORMAT = "Comment with id %s not found";

    public final static String GENRE_MESSAGE_FORMAT = "Genre with id %s not found";

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String messageFormat, String id) {
        super(messageFormat.formatted(id));
    }
}
