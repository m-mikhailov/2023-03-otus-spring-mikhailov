package ru.mikhailov.otus.task2.service;

import ru.mikhailov.otus.task2.domain.Answer;
import ru.mikhailov.otus.task2.domain.Question;

import java.util.List;

public interface QuestionAnswerConverter {

    String convertQuestionToString(Question value);

    String convertAnswersToString(List<Answer> answers);

}
