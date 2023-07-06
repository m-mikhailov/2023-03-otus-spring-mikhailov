package ru.mikhailov.otus.task7.domain.dto;

public record BookUpdateDto(
        Long id,

        String name,
        Long authorId,
        Long genreId
) {
}
