package ru.mikhailov.otus.task7.domain.error;

public class GenreNotFoundException extends EntityNotFoundException {

    private final static String MESSAGE_FORMAT = "Genre with id %s not found";

    public GenreNotFoundException(Long id) {
        super(MESSAGE_FORMAT.formatted(id));
    }
}
