package ru.mikhailov.otus.task2.service;

import ru.mikhailov.otus.task2.domain.Question;
import ru.mikhailov.otus.task2.domain.QuestionAnswer;

public interface QuestionAnswerConverter {

    String convertQuestionToString(Question value);

    String convertAnswerToString(QuestionAnswer value);

}
