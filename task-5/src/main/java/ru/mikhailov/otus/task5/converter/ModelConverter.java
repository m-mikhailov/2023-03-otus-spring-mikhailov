package ru.mikhailov.otus.task5.converter;

import java.util.List;
import java.util.stream.Collectors;

public interface ModelConverter <T> {

    String modelToString(T model);

    default String modelToString(List<T> models) {
        return models
                .stream()
                .map(this::modelToString)
                .collect(Collectors.joining("\n"));
    }

}
