package ru.mikhailov.otus.task2.domain.error;

public class QuestionNotFoundException extends RuntimeException {

    private static String message = "Question with ID %s not found.";

    public QuestionNotFoundException(Long id) {
        super(message.formatted(id));
    }

}
