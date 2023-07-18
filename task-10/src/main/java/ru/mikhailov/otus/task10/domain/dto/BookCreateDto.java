package ru.mikhailov.otus.task10.domain.dto;

public record BookCreateDto(
        String name,
        Long authorId,
        Long genreId
) {
}
