package ru.mikhailov.otus.task2.service;

import java.util.List;

public interface QuestionAnswerService {

    List<Long> getQuestionIds();
    void printQuestion(Long questionId);

}
