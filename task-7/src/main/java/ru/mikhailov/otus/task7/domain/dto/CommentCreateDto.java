package ru.mikhailov.otus.task7.domain.dto;

public record CommentCreateDto(
        String text,
        Long bookId
) {
}
