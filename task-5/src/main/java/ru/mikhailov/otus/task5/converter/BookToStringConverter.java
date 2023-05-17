package ru.mikhailov.otus.task5.converter;

import org.springframework.stereotype.Component;
import ru.mikhailov.otus.task5.domain.model.Book;

@Component
public class BookToStringConverter implements ModelConverter<Book> {
    @Override
    public String modelToString(Book model) {
        return "%s. Название: %s. Автор: %s. Жанр: %s.".formatted(
                model.getId(),
                model.getName(),
                model.getAuthor().getName(),
                model.getGenre().getName()
        );
    }
}
