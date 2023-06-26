package ru.mikhailov.otus.task6.domain.dto;

public record CommentDto(
        String text,
        Long bookId
) {
}
