package ru.mikhailov.otus.task1.service;

import org.springframework.core.io.ClassPathResource;
import ru.mikhailov.otus.task1.domain.Constant;
import ru.mikhailov.otus.task1.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

public class QuestionCSVReader implements QuestionReaderService {

    private String fileName;

    public QuestionCSVReader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Question> readQuestions() {
        ClassPathResource resource = new ClassPathResource(fileName);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream()))
        ) {
            return reader.lines()
                    .map(this::parseQuestion)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Resource reader has failed", e);
        }
    }

    private Optional<Question> parseQuestion(String row) {
        var values = row.split(Constant.QUESTIONS_ANSWERS_DELIMITER);
        if (row.isBlank() || values.length > 2) {
            // Skip this invalid row
            return Optional.empty();
        }
        List<String> answers = values.length == 1 ? List.of() : parseAnswers(values[1]);
        return Optional.of(new Question(values[0], answers));
    }

    private List<String> parseAnswers(String row) {
        return List.of(row.split(Constant.ANSWERS_DELIMITER, -1));
    }
}
