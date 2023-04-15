package ru.mikhailov.otus.task2.service;

import org.springframework.stereotype.Component;
import ru.mikhailov.otus.task2.domain.Question;
import ru.mikhailov.otus.task2.domain.QuestionAnswer;

@Component
public class QuestionAnswerConverterImpl implements QuestionAnswerConverter {

    @Override
    public String convertQuestionToString(Question value) {
        return String.format("%s. %s", value.id(), value.question());
    }

    @Override
    public String convertAnswerToString(QuestionAnswer value) {
        StringBuilder sb = new StringBuilder();
        char idx = 'a';
        for (String answer: value.answers()) {
            sb.append(String.format("%s. %s\n", (char)idx++, answer));
        }
        return sb.toString();
    }
}
