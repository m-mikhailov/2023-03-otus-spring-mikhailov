package ru.mikhailov.otus.task3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task3.config.properties.ResourceProvider;
import ru.mikhailov.otus.task3.domain.Answer;
import ru.mikhailov.otus.task3.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class QuestionDaoServiceImpl implements QuestionDaoService {

    public static final String QUESTIONS_ANSWERS_DELIMITER = ";";

    public static final String ANSWERS_DELIMITER = ",";

    private enum CsvFields {
        ID,
        QUESTION,
        ANSWER,
        CORRECT_ANSWER
    }

    private final ResourceProvider resourceProvider;

    @Override
    public List<Question> getAll() {
        return readCsvFile(resourceProvider.getFileName(), this::parseQuestions);
    }

    private List<Question> readCsvFile(String fileName, Function<BufferedReader, List<Question>> func) {
        ClassPathResource resource = new ClassPathResource(fileName);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream()))
        ) {
            return func.apply(reader);
        } catch (IOException e) {
            throw new RuntimeException("Resource reader has failed", e);
        }
    }

    private List<Question> parseQuestions(BufferedReader reader) {
        return reader.lines()
                .map(this::parseLine)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private Optional<Question> parseLine(String line) {
        var values = line.split(QUESTIONS_ANSWERS_DELIMITER, -1);
        if (line.isBlank() || values.length > CsvFields.values().length) {
            // Skip this invalid row
            return Optional.empty();
        }
        var questionId = Long.parseLong(values[CsvFields.ID.ordinal()]);
        var answers = parseAnswers(questionId, values[CsvFields.ANSWER.ordinal()]);
        var correctAnswers = parseAnswers(questionId, values[CsvFields.CORRECT_ANSWER.ordinal()]);
        return Optional.of(new Question(
                questionId,
                values[CsvFields.QUESTION.ordinal()],
                answers,
                correctAnswers
        ));
    }

    private List<Answer> parseAnswers(Long questionId, String fieldValue) {
        return Arrays.stream(fieldValue.split(ANSWERS_DELIMITER))
                .filter(value -> !value.isBlank())
                .map(value -> new Answer(questionId, value))
                .toList();

    }

}
