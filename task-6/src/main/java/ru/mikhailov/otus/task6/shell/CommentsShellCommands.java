package ru.mikhailov.otus.task6.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.mikhailov.otus.task6.domain.model.Comment;
import ru.mikhailov.otus.task6.repository.CommentRepository;

@ShellComponent
@RequiredArgsConstructor
public class CommentsShellCommands {

    private final CommentRepository repository;

    @ShellMethod(value = "Update comment", key = "comments update")
    public void updateComment(Long id, String text) {
        var commentUpdate = new Comment(id, text, null);

        repository.update(commentUpdate);
    }

    @ShellMethod(value = "Delete comment", key = "comments delete")
    public void deleteComment(Long id) {
        repository.deleteById(id);
    }

}
