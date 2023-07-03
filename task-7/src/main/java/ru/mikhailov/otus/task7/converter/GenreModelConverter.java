package ru.mikhailov.otus.task7.converter;

import org.springframework.stereotype.Component;
import ru.mikhailov.otus.task7.domain.dto.GenreDto;

@Component
public class GenreModelConverter implements ModelConverter<GenreDto> {
    @Override
    public String modelToString(GenreDto model) {
        return "%s. %s".formatted(model.id(), model.name());
    }
}
