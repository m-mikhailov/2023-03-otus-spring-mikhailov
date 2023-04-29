package ru.mikhailov.otus.task3.service;

import ru.mikhailov.otus.task3.domain.Answer;
import ru.mikhailov.otus.task3.domain.Student;

import java.util.List;

public interface QuizScoreService {

    int calculate(List<Answer> answers);

    void printResult(Student student, int score);

}

