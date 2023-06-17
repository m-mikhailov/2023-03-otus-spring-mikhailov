package ru.mikhailov.otus.task6.converter;

import org.springframework.stereotype.Component;
import ru.mikhailov.otus.task6.domain.model.Genre;

@Component
public class GenreModelConverter implements ModelConverter<Genre> {
    @Override
    public String modelToString(Genre model) {
        return "%s. %s".formatted(model.getId(), model.getName());
    }
}
