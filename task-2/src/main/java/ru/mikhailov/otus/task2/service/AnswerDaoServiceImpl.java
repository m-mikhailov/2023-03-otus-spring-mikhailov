package ru.mikhailov.otus.task2.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task2.domain.QuestionAnswer;
import ru.mikhailov.otus.task2.service.reader.CsvReaderService;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerDaoServiceImpl implements AnswerDaoService {

    private final List<QuestionAnswer> answers;

    public AnswerDaoServiceImpl(@Qualifier("answerReaderService") CsvReaderService<QuestionAnswer> reader) {
        this.answers = reader.readFile();
    }

    @Override
    public Optional<QuestionAnswer> getById(Long questionId) {
        return answers.stream()
                .filter(answer -> answer.questionId().equals(questionId))
                .findFirst();
    }
}
