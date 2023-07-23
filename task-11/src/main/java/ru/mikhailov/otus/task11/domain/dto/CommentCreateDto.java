package ru.mikhailov.otus.task11.domain.dto;

public record CommentCreateDto(
        String text,
        String bookId
) {
}
