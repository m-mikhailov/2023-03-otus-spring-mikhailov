package ru.mikhailov.otus.task6.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.mikhailov.otus.task6.converter.ModelConverter;
import ru.mikhailov.otus.task6.repository.GenreRepository;
import ru.mikhailov.otus.task6.domain.model.Genre;

@ShellComponent
@RequiredArgsConstructor
public class GenresShellCommands {

    private final GenreRepository repository;

    private final ModelConverter<Genre> modelConverter;

    @ShellMethod("Show genres")
    public String genres(@ShellOption(defaultValue = "0") Long id) {
        if (id == 0) {
            return modelConverter.modelToString(repository.findAll());
        }

        return modelConverter.modelToString(repository.findById(id));
    }

    @ShellMethod(value = "Create new genre", key = "genres create")
    public String createGenre(String name) {
        var savedGenre = repository.save(new Genre(null, name));

        return modelConverter.modelToString(savedGenre);
    }

}
