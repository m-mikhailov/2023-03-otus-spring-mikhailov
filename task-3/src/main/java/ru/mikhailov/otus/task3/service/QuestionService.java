package ru.mikhailov.otus.task3.service;

import ru.mikhailov.otus.task3.domain.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getAll();

    void print(Question question);

}
