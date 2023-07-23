package ru.mikhailov.otus.task10.domain.dto;

public record CommentCreateDto(
        String text,
        Long bookId
) {
}
