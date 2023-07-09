package ru.mikhailov.otus.task8.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.mikhailov.otus.task8.domain.dto.CommentCreateDto;
import ru.mikhailov.otus.task8.domain.dto.CommentUpdateDto;
import ru.mikhailov.otus.task8.service.CommentService;

@ShellComponent
@RequiredArgsConstructor
public class CommentsShellCommands {

    private final CommentService service;

    @ShellMethod(value = "Update comment", key = "comments update")
    public void updateComment(String id, String text) {
        var commentUpdate = new CommentUpdateDto(id, text);

        service.update(commentUpdate);
    }

    @ShellMethod(value = "Delete comment", key = "comments delete")
    public void deleteComment(String id) {
        service.deleteById(id);
    }

    @ShellMethod(value = "Add new book comment", key = "comments add")
    public void addNewComment(@ShellOption(value = "book-id") String bookId, String text) {
        var newComment = new CommentCreateDto(text, bookId);

        service.add(newComment);
    }

}
