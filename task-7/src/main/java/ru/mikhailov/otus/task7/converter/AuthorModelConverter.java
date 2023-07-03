package ru.mikhailov.otus.task7.converter;

import org.springframework.stereotype.Component;
import ru.mikhailov.otus.task7.domain.dto.AuthorDto;

@Component
public class AuthorModelConverter implements ModelConverter<AuthorDto> {
    @Override
    public String modelToString(AuthorDto model) {
        return "%s. %s".formatted(model.id(), model.name());
    }
}
