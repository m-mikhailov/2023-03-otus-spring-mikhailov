package ru.mikhailov.otus.task6.converter;

import org.springframework.stereotype.Component;
import ru.mikhailov.otus.task6.domain.model.Comment;

import java.util.List;

@Component
public class CommentModelConverter implements ModelConverter<Comment> {
    @Override
    public String modelToString(Comment model) {
        return "%s. %s".formatted(model.getId(), model.getText());
    }

    @Override
    public String modelToString(List<Comment> models) {
        StringBuilder sb = new StringBuilder("Комментарии:\n");
        return sb.append(ModelConverter.super.modelToString(models)).toString();
    }
}
