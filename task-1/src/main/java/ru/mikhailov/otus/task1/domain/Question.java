package ru.mikhailov.otus.task1.domain;

import java.util.List;

public record Question(
        String body,
        List<String> answers
) {

    public void print() {
        System.out.printf("# %s%n", body);
        if (!answers.isEmpty()) {
            char ind = 'A';
            for (int i = 0; i < answers.size(); i++) {
                System.out.printf("%s. %s%n", (char) (ind + i), answers.get(i));
            }
        }
    }

}
