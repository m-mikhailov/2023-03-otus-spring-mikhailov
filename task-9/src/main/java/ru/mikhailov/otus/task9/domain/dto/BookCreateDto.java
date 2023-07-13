package ru.mikhailov.otus.task9.domain.dto;

public record BookCreateDto(
        String name,
        Long authorId,
        Long genreId
) {
}
