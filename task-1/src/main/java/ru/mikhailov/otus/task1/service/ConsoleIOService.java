package ru.mikhailov.otus.task1.service;

import ru.mikhailov.otus.task1.domain.Question;

public class ConsoleIOService implements IOService {

    @Override
    public void print(Question question) {
        System.out.printf("# %s%n", question.body());
        if (!question.answers().isEmpty()) {
            char ind = 'A';
            for (int i = 0; i < question.answers().size(); i++) {
                System.out.printf("%s. %s%n", (char) (ind + i), question.answers().get(i));
            }
        }
    }



}
