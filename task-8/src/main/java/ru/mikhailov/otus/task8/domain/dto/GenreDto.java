package ru.mikhailov.otus.task8.domain.dto;


import ru.mikhailov.otus.task8.domain.model.Genre;

public record GenreDto(
        String id,
        String name
) {

    public GenreDto(Genre genre) {
        this(genre.getId(), genre.getName());
    }
}
