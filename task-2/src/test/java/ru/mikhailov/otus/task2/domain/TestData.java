package ru.mikhailov.otus.task2.domain;

import java.util.ArrayList;
import java.util.List;

public final class TestData {

    public static final Question QUESTION_1 = new Question(1L, "question 1");

    public static final Question QUESTION_2 = new Question(2L, "question 2");

    public static final Question QUESTION_3 = new Question(3L, "question 3");

    public static final List<Question> QUESTIONS_CSV_DATA = List.of(
            QUESTION_1,
            QUESTION_2,
            QUESTION_3
    );

}
