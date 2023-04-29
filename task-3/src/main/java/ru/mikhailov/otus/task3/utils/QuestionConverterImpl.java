package ru.mikhailov.otus.task3.utils;

import org.springframework.stereotype.Component;
import ru.mikhailov.otus.task3.domain.Answer;
import ru.mikhailov.otus.task3.domain.Question;

@Component
public class QuestionConverterImpl implements QuestionConverter {

    @Override
    public String convertQuestionToString(Question question) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s. %s\n", question.id(), question.question()));
        char idx = 'a';
        for (Answer answer : question.answers()) {
            sb.append(String.format("%s. %s\n", (char) idx++, answer.answer()));
        }
        return sb.toString();
    }

}
