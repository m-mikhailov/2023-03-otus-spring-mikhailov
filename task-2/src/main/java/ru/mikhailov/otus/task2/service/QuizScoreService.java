package ru.mikhailov.otus.task2.service;

import ru.mikhailov.otus.task2.domain.Answer;
import ru.mikhailov.otus.task2.domain.Student;

import java.util.List;

public interface QuizScoreService {

    int calculate(List<Answer> answers);

    void printResult(Student student, int score);

}

