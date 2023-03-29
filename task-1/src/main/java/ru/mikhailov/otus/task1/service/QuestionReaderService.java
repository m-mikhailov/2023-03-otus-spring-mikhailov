package ru.mikhailov.otus.task1.service;

import ru.mikhailov.otus.task1.domain.Question;

import java.util.List;

public interface QuestionReaderService {

    List<Question> readQuestions();

}
