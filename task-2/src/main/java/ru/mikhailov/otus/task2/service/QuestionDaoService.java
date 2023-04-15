package ru.mikhailov.otus.task2.service;

import ru.mikhailov.otus.task2.domain.Question;

import java.util.List;

public interface QuestionDaoService {

    List<Long> getIds();

    Question getById(Long id);

}
