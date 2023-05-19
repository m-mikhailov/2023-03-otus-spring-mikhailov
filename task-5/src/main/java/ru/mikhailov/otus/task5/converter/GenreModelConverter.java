package ru.mikhailov.otus.task5.converter;

import org.springframework.stereotype.Component;
import ru.mikhailov.otus.task5.domain.model.Genre;

@Component
public class GenreModelConverter implements ModelConverter<Genre> {
    @Override
    public String modelToString(Genre model) {
        return "%s. %s".formatted(model.getId(), model.getName());
    }
}
