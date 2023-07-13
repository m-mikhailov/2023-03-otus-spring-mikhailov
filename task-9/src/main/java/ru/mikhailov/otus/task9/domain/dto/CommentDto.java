package ru.mikhailov.otus.task9.domain.dto;

import ru.mikhailov.otus.task9.domain.model.Comment;

public record CommentDto(
        Long id,
        String text
) {

    public CommentDto(Comment comment) {
        this(comment.getId(), comment.getText());
    }
}
