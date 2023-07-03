package ru.mikhailov.otus.task7.domain.error;

public class BookNotFoundException extends EntityNotFoundException {

    private final static String MESSAGE_FORMAT = "Book with id %s not found";

    public BookNotFoundException(Long id) {
        super(MESSAGE_FORMAT.formatted(id));
    }
}
