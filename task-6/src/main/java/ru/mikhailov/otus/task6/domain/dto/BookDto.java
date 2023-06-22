package ru.mikhailov.otus.task6.domain.dto;

public record BookDto(
        Long id,
        String name,
        Long authorId,
        Long genreId
) {
}
