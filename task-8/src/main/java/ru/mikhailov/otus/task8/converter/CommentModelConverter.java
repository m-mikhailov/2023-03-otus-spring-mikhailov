package ru.mikhailov.otus.task8.converter;

import org.springframework.stereotype.Component;
import ru.mikhailov.otus.task8.domain.dto.CommentDto;

import java.util.List;

@Component
public class CommentModelConverter implements ModelConverter<CommentDto> {
    @Override
    public String modelToString(CommentDto model) {
        return "%s. %s".formatted(model.id(), model.text());
    }

    @Override
    public String modelToString(List<CommentDto> models) {
        StringBuilder sb = new StringBuilder("Комментарии:\n");
        return sb.append(ModelConverter.super.modelToString(models)).toString();
    }
}
