package ru.mikhailov.otus.task7.domain.error;

public class CommentNotFoundException extends EntityNotFoundException {

    private final static String MESSAGE_FORMAT = "Comment with id %s not found";

    public CommentNotFoundException(Long id) {
        super(MESSAGE_FORMAT.formatted(id));
    }
}
