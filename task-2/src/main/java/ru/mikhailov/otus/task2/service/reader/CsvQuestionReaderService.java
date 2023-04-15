package ru.mikhailov.otus.task2.service.reader;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task2.config.properties.ApplicationProperties;
import ru.mikhailov.otus.task2.domain.Constant;
import ru.mikhailov.otus.task2.domain.Question;
import ru.mikhailov.otus.task2.service.reader.util.ResourceFileReader;

import java.io.BufferedReader;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class CsvQuestionReaderService implements CsvReaderService<Question> {

    private final String fileName;

    @Override
    public List<Question> readFile() {
        return ResourceFileReader.read(fileName, this::parseQuestions);
    }

    private List<Question> parseQuestions(BufferedReader reader) {
        return reader.lines()
                .map(this::parseLine)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private Optional<Question> parseLine(String line) {
        var values = line.split(Constant.QUESTIONS_ANSWERS_DELIMITER);
        if (line.isBlank() || values.length > 2) {
            // Skip this invalid row
            return Optional.empty();
        }
        return Optional.of(new Question(Long.parseLong(values[0]), values[1]));
    }

}
