package ru.mikhailov.otus.task8.domain.dto;


import ru.mikhailov.otus.task8.domain.model.Comment;

public record CommentDto(
        String id,
        String text
) {

    public CommentDto(Comment comment) {
        this(comment.getId(), comment.getText());
    }
}
