package ru.mikhailov.otus.task6.shell;

import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.mikhailov.otus.task6.converter.ModelConverter;
import ru.mikhailov.otus.task6.domain.dto.BookDto;
import ru.mikhailov.otus.task6.domain.dto.BookEntityDto;
import ru.mikhailov.otus.task6.domain.model.Book;
import ru.mikhailov.otus.task6.domain.model.Comment;
import ru.mikhailov.otus.task6.service.BookService;
import ru.mikhailov.otus.task6.service.CommentService;

@ShellComponent
@RequiredArgsConstructor
public class BooksShellCommands {

    public static final String DEFAULT_VALUE = "default";

    public static final String DEFAULT_ID = "0";

    private final BookService bookService;

    private final CommentService commentService;

    private final ModelConverter<BookEntityDto> bookModelConverter;
    private final ModelConverter<Comment> commentModelConverter;

    @ShellMethod("Show books")
    public String books(@ShellOption(defaultValue = "0") Long id) {
        if (id == 0) {
            return bookModelConverter.modelToString(bookService.findAll());
        }

        return bookModelConverter.modelToString(bookService.findById(id));
    }

    @ShellMethod(value = "Create new book", key = "books create")
    public String createBook(
            @NotEmpty String name,
            @ShellOption(value = "author-id") Long authorId,
            @ShellOption(value = "genre-id") Long genreId
    ) {
        var bookDto = new BookDto(null, name, authorId, genreId);

        var savedBook = bookService.save(bookDto);

        return bookModelConverter.modelToString(savedBook);
    }

    @ShellMethod(value = "Update book", key = "books update")
    public void updateBook(
            Long id,
            @ShellOption(defaultValue = DEFAULT_VALUE) String name,
            @ShellOption(defaultValue = DEFAULT_ID, value = "author-id") Long authorId,
            @ShellOption(defaultValue = DEFAULT_ID, value = "genre-id") Long genreId
    ) {
        var bookDto = new BookDto(
                id,
                name.equals(DEFAULT_VALUE) ? null : name,
                authorId == 0 ? null : authorId,
                genreId == 0 ? null : genreId);

        bookService.update(bookDto);
    }

    @ShellMethod(value = "Delete book", key = "books delete")
    public void deleteBook(Long id) {
        bookService.deleteById(id);
    }

    @ShellMethod(value = "Show book comments", key = "book comments")
    public String showBookComments(Long id) {
        var comments = commentService.findAllByBookId(id);
        if (comments.isEmpty()) {
            return "Здесь пока ничего нет :(";
        }
        return commentModelConverter.modelToString(comments);
    }

}
