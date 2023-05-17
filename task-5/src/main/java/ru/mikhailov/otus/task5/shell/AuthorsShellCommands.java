package ru.mikhailov.otus.task5.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.mikhailov.otus.task5.converter.ModelConverter;
import ru.mikhailov.otus.task5.dao.AuthorDao;
import ru.mikhailov.otus.task5.domain.model.Author;

@ShellComponent
@RequiredArgsConstructor
public class AuthorsShellCommands {

    private final AuthorDao authorDao;

    private final ModelConverter<Author> modelConverter;

    @ShellMethod("Show authors")
    public String authors(@ShellOption(defaultValue = "0") Long id) {
        if (id == 0) {
            return modelConverter.modelToString(authorDao.findAll());
        }

        return modelConverter.modelToString(authorDao.findById(id));
    }

    @ShellMethod(value = "Create new author", key = "authors create")
    public String createAuthor(String name) {
        var savedAuthor = authorDao.save(new Author(null, name));

        return modelConverter.modelToString(savedAuthor);
    }

}
