package ru.mikhailov.otus.task9.domain.dto;

public record CommentCreateDto(
        String text,
        Long bookId
) {
}
