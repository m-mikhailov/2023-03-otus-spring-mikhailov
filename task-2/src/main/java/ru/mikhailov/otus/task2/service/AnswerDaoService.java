package ru.mikhailov.otus.task2.service;

import ru.mikhailov.otus.task2.domain.QuestionAnswer;

import java.util.Optional;

public interface AnswerDaoService {

    Optional<QuestionAnswer> getById(Long questionId);

}
