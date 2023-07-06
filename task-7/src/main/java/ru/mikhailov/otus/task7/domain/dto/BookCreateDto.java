package ru.mikhailov.otus.task7.domain.dto;

public record BookCreateDto(
        String name,
        Long authorId,
        Long genreId
) {
}
