package ru.mikhailov.otus.task5.domain.dto;

public record BookDto(
        String name,
        Long authorId,
        Long genreId
) {
}
