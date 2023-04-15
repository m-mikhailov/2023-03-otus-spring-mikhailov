package ru.mikhailov.otus.task2.service.reader;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task2.config.properties.ApplicationProperties;
import ru.mikhailov.otus.task2.domain.Answer;
import ru.mikhailov.otus.task2.domain.Constant;
import ru.mikhailov.otus.task2.domain.QuestionAnswer;
import ru.mikhailov.otus.task2.service.reader.util.ResourceFileReader;

import java.io.BufferedReader;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;


@RequiredArgsConstructor
public class CsvAnswerReaderService implements CsvReaderService<QuestionAnswer> {

    private final String fileName;

    @Override
    public List<QuestionAnswer> readFile() {
        return ResourceFileReader.read(fileName, this::parseAnswers);
    }

    private List<QuestionAnswer> parseAnswers(BufferedReader reader) {
        return reader
                .lines()
                .map(this::parseLine)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(groupingBy(Answer::questionId))
                .entrySet()
                .stream()
                .map(this::fromEntry)
                .toList();
    }

    private Optional<Answer> parseLine(String line) {
        var values = line.split(Constant.QUESTIONS_ANSWERS_DELIMITER);
        if (line.isBlank() || values.length > 2) {
            // Skip this invalid row
            return Optional.empty();
        }
        return Optional.of(new Answer(Long.parseLong(values[0]), values[1]));
    }

    private QuestionAnswer fromEntry(Map.Entry<Long, List<Answer>> entry) {
        var answers = entry.getValue()
                .stream()
                .map(Answer::answer)
                .toList();
        return new QuestionAnswer(entry.getKey(), answers);
    }

}
