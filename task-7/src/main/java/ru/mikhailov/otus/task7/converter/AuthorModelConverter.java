package ru.mikhailov.otus.task7.converter;

import org.springframework.stereotype.Component;
import ru.mikhailov.otus.task7.domain.model.Author;

@Component
public class AuthorModelConverter implements ModelConverter<Author> {
    @Override
    public String modelToString(Author model) {
        return "%s. %s".formatted(model.getId(), model.getName());
    }
}
