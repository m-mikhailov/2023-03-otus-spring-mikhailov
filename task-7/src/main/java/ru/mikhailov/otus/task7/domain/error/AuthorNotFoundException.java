package ru.mikhailov.otus.task7.domain.error;

public class AuthorNotFoundException extends EntityNotFoundException {

    private final static String MESSAGE_FORMAT = "Author with id %s not found";

    public AuthorNotFoundException(Long id) {
        super(MESSAGE_FORMAT.formatted(id));
    }
}
