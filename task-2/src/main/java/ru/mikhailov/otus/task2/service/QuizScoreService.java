package ru.mikhailov.otus.task2.service;

import ru.mikhailov.otus.task2.domain.QuestionAnswer;
import ru.mikhailov.otus.task2.domain.Student;

import java.util.List;

public interface QuizScoreService {

    int calculate(List<QuestionAnswer> answers);

    void printResult(Student student, int score);

}

