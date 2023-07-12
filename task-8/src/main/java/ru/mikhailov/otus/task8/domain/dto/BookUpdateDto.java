package ru.mikhailov.otus.task8.domain.dto;

public record BookUpdateDto(
        String id,

        String name,
        String authorId,
        String genreId
) {
}
