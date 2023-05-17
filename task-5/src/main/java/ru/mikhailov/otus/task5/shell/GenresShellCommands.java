package ru.mikhailov.otus.task5.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.mikhailov.otus.task5.converter.ModelConverter;
import ru.mikhailov.otus.task5.dao.GenreDao;
import ru.mikhailov.otus.task5.domain.model.Genre;

@ShellComponent
@RequiredArgsConstructor
public class GenresShellCommands {

    private final GenreDao genreDao;

    private final ModelConverter<Genre> modelConverter;

    @ShellMethod("Show genres")
    public String genres(@ShellOption(defaultValue = "0") Long id) {
        if (id == 0) {
            return modelConverter.modelToString(genreDao.findAll());
        }

        return modelConverter.modelToString(genreDao.findById(id));
    }

    @ShellMethod(value = "Create new genre", key = "genres create")
    public String createGenre(String name) {
        var savedGenre = genreDao.save(new Genre(null, name));

        return modelConverter.modelToString(savedGenre);
    }

}
