package ru.mikhailov.otus.task7.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.mikhailov.otus.task7.domain.dto.CommentDto;
import ru.mikhailov.otus.task7.domain.model.Comment;
import ru.mikhailov.otus.task7.service.CommentService;

@ShellComponent
@RequiredArgsConstructor
public class CommentsShellCommands {

    private final CommentService service;

    @ShellMethod(value = "Update comment", key = "comments update")
    public void updateComment(Long id, String text) {
        var commentUpdate = new Comment(id, text, null);

        service.update(commentUpdate);
    }

    @ShellMethod(value = "Delete comment", key = "comments delete")
    public void deleteComment(Long id) {
        service.deleteById(id);
    }

    @ShellMethod(value = "Add new book comment", key = "comments add")
    public void addNewComment(@ShellOption(value = "book-id") Long bookId, String text) {
        var newComment = new CommentDto(text, bookId);

        service.add(newComment);
    }

}
