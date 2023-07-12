package ru.mikhailov.otus.task8.domain.dto;

public record CommentCreateDto(
        String text,
        String bookId
) {
}
