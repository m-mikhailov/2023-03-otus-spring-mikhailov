package ru.mikhailov.otus.task6.converter;

import org.springframework.stereotype.Component;
import ru.mikhailov.otus.task6.domain.dto.BookEntityDto;

@Component
public class BookModelConverter implements ModelConverter<BookEntityDto> {
    @Override
    public String modelToString(BookEntityDto model) {
        return "%s. Название: %s. Автор: %s. Жанр: %s.".formatted(
                model.id(),
                model.name(),
                model.author().getName(),
                model.genre().getName()
        );
    }
}
