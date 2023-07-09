package ru.mikhailov.otus.task8.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.mikhailov.otus.task8.converter.ModelConverter;
import ru.mikhailov.otus.task8.domain.dto.GenreCreateDto;
import ru.mikhailov.otus.task8.domain.dto.GenreDto;
import ru.mikhailov.otus.task8.service.GenreService;

import java.util.Objects;

@ShellComponent
@RequiredArgsConstructor
public class GenresShellCommands {

    private final GenreService service;

    private final ModelConverter<GenreDto> modelConverter;

    @ShellMethod("Show genres")
    public String genres(@ShellOption(defaultValue = "0") String id) {
        if (id.equals("0")) {
            return modelConverter.modelToString(service.findAll());
        }

        return modelConverter.modelToString(service.findById(id));
    }

    @ShellMethod(value = "Create new genre", key = "genres create")
    public String createGenre(String name) {
        var savedGenre = service.save(new GenreCreateDto(name));

        return modelConverter.modelToString(savedGenre);
    }

}
