package ru.mikhailov.otus.task2.service;

import org.springframework.stereotype.Component;
import ru.mikhailov.otus.task2.domain.Answer;
import ru.mikhailov.otus.task2.domain.Question;

import java.util.List;

@Component
public class QuestionAnswerConverterImpl implements QuestionAnswerConverter {

    @Override
    public String convertQuestionToString(Question value) {
        return String.format("%s. %s", value.id(), value.question());
    }

    @Override
    public String convertAnswersToString(List<Answer> answers) {
        StringBuilder sb = new StringBuilder();
        char idx = 'a';
        for (Answer answer : answers) {
            sb.append(String.format("%s. %s\n", (char) idx++, answer.answer()));
        }
        return sb.toString();
    }
}
