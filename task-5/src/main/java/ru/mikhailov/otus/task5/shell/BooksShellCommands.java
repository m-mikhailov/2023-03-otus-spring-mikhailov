package ru.mikhailov.otus.task5.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.mikhailov.otus.task5.converter.ModelConverter;
import ru.mikhailov.otus.task5.domain.dto.BookDto;
import ru.mikhailov.otus.task5.domain.model.Book;
import ru.mikhailov.otus.task5.service.BookService;

@ShellComponent
@RequiredArgsConstructor
public class BooksShellCommands {

    public static final String DEFAULT_VALUE = "default";

    public static final String DEFAULT_ID = "0";

    private final BookService bookService;

    private final ModelConverter<Book> modelConverter;

    @ShellMethod("Show books")
    public String books(@ShellOption(defaultValue = "0") Long id) {
        if (id == 0) {
            return modelConverter.modelToString(bookService.findAll());
        }

        return modelConverter.modelToString(bookService.findById(id));
    }

    @ShellMethod(value = "Create new book", key = "books create")
    public String createBook(
            String name,
            @ShellOption(value = "author-id")Long authorId,
            @ShellOption(value = "genre-id")Long genreId
    ) {
        var bookDto = new BookDto(name, authorId, genreId);

        var savedAuthor = bookService.save(bookDto);

        return modelConverter.modelToString(savedAuthor);
    }

    @ShellMethod(value = "Update book", key = "books update")
    public void updateBook(
            Long id,
            @ShellOption(defaultValue = DEFAULT_VALUE) String name,
            @ShellOption(defaultValue = DEFAULT_ID, value = "author-id") Long authorId,
            @ShellOption(defaultValue = DEFAULT_ID, value = "genre-id") Long genreId
    ) {
        var bookDto = new BookDto(
                name.equals(DEFAULT_VALUE) ? null : name,
                authorId == 0 ? null : authorId,
                genreId == 0 ? null : genreId);

        bookService.updateById(id, bookDto);
    }

    @ShellMethod(value = "Delete book", key = "books delete")
    public void deleteBook(Long id) {
        bookService.deleteById(id);
    }



}
