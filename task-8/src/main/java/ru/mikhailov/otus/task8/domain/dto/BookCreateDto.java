package ru.mikhailov.otus.task8.domain.dto;

public record BookCreateDto(
        String name,
        String authorId,
        String genreId
) {
}
