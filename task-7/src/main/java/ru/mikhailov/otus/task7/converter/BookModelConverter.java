package ru.mikhailov.otus.task7.converter;

import org.springframework.stereotype.Component;
import ru.mikhailov.otus.task7.domain.dto.BookDto;

@Component
public class BookModelConverter implements ModelConverter<BookDto> {
    @Override
    public String modelToString(BookDto model) {
        return "%s. Название: %s. Автор: %s. Жанр: %s.".formatted(
                model.id(),
                model.name(),
                model.author().getName(),
                model.genre().getName()
        );
    }
}
