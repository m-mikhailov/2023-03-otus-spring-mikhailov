package ru.mikhailov.otus.task6.domain.dto;

public record BookDto(
        String name,
        Long authorId,
        Long genreId
) {
}
