package ru.mikhailov.otus.task3.service;

import ru.mikhailov.otus.task3.domain.Question;

import java.util.List;

public interface QuestionDaoService {
    List<Question> getAll();

}
