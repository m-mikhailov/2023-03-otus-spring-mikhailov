package ru.mikhailov.otus.task4.service;

import ru.mikhailov.otus.task4.domain.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getAll();

    void print(Question question);

}
