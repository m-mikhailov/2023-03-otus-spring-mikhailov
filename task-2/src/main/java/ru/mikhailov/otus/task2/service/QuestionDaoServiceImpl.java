package ru.mikhailov.otus.task2.service;

import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task2.domain.Question;
import ru.mikhailov.otus.task2.domain.error.QuestionNotFoundException;
import ru.mikhailov.otus.task2.service.reader.CsvReaderService;

import java.util.List;

@Service
public class QuestionDaoServiceImpl implements QuestionDaoService {

    private final List<Question> questions;

    public QuestionDaoServiceImpl(CsvReaderService<Question> reader) {
        this.questions = reader.readFile();
    }

    @Override
    public List<Long> getIds() {
        return questions.stream().map(Question::id).toList();
    }

    @Override
    public Question getById(Long id) {
        return questions.stream()
                .filter(question -> question.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new QuestionNotFoundException(id));
    }
}
