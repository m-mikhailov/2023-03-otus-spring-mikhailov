package ru.mikhailov.otus.task2.domain;

import java.util.List;

public record QuestionAnswer(
        Long questionId,
        List<String> answers
) {
}
