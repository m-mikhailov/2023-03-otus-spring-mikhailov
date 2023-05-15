package ru.mikhailov.otus.task3.domain;

import java.util.List;

public record Question(
        Long id,
        String question,
        List<Answer> answers,
        List<Answer> correctAnswers
) {
}
