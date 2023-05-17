package ru.mikhailov.otus.task5.converter;

import org.springframework.stereotype.Component;
import ru.mikhailov.otus.task5.domain.model.Author;

@Component
public class AuthorToStringConverter implements ModelConverter<Author> {
    @Override
    public String modelToString(Author model) {
        return "%s. %s".formatted(model.getId(), model.getName());
    }
}
