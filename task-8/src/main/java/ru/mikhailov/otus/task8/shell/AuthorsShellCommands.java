package ru.mikhailov.otus.task8.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.mikhailov.otus.task8.converter.ModelConverter;
import ru.mikhailov.otus.task8.domain.dto.AuthorCreateDto;
import ru.mikhailov.otus.task8.domain.dto.AuthorDto;
import ru.mikhailov.otus.task8.service.AuthorService;

@ShellComponent
@RequiredArgsConstructor
public class AuthorsShellCommands {

    private final AuthorService service;

    private final ModelConverter<AuthorDto> modelConverter;

    @ShellMethod("Show authors")
    public String authors(@ShellOption(defaultValue = "0") String id) {
        if (id.equals("0")) {
            return modelConverter.modelToString(service.findAll());
        }

        return modelConverter.modelToString(service.findById(id));
    }

    @ShellMethod(value = "Create new author", key = "authors create")
    public String createAuthor(String name) {
        var savedAuthor = service.create(new AuthorCreateDto(name));

        return modelConverter.modelToString(savedAuthor);
    }

}
