package ru.mikhailov.otus.task9.domain.dto;

import ru.mikhailov.otus.task9.domain.model.Genre;

public record GenreDto(
        Long id,
        String name
) {

    public GenreDto(Genre genre) {
        this(genre.getId(), genre.getName());
    }
}
