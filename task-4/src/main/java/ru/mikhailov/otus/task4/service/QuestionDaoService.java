package ru.mikhailov.otus.task4.service;


import ru.mikhailov.otus.task4.domain.Question;

import java.util.List;

public interface QuestionDaoService {
    List<Question> getAll();

}
