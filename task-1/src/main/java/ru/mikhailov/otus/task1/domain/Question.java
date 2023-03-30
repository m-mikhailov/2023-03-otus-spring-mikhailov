package ru.mikhailov.otus.task1.domain;

import java.util.List;

public record Question(
        String body,
        List<String> answers
) {
}
