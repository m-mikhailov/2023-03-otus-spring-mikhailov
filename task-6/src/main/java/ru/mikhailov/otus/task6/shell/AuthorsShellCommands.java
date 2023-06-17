package ru.mikhailov.otus.task6.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.mikhailov.otus.task6.converter.ModelConverter;
import ru.mikhailov.otus.task6.repository.AuthorRepository;
import ru.mikhailov.otus.task6.domain.model.Author;

@ShellComponent
@RequiredArgsConstructor
public class AuthorsShellCommands {

    private final AuthorRepository repository;

    private final ModelConverter<Author> modelConverter;

    @ShellMethod("Show authors")
    public String authors(@ShellOption(defaultValue = "0") Long id) {
        if (id == 0) {
            return modelConverter.modelToString(repository.findAll());
        }

        return modelConverter.modelToString(repository.findById(id));
    }

    @ShellMethod(value = "Create new author", key = "authors create")
    public String createAuthor(String name) {
        var savedAuthor = repository.save(new Author(null, name));

        return modelConverter.modelToString(savedAuthor);
    }

}
