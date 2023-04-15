package ru.mikhailov.otus.task2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mikhailov.otus.task2.config.properties.ApplicationProperties;
import ru.mikhailov.otus.task2.domain.Question;
import ru.mikhailov.otus.task2.domain.QuestionAnswer;
import ru.mikhailov.otus.task2.service.io.IOService;
import ru.mikhailov.otus.task2.service.io.IOStreamsService;
import ru.mikhailov.otus.task2.service.reader.CsvAnswerReaderService;
import ru.mikhailov.otus.task2.service.reader.CsvQuestionReaderService;
import ru.mikhailov.otus.task2.service.reader.CsvReaderService;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final ApplicationProperties properties;

    @Bean
    public IOService ioStreamsService() {
        return new IOStreamsService(System.out, System.in);
    }

    @Bean
    public CsvReaderService<Question> csvQuestionReaderService() {
        return new CsvQuestionReaderService(properties.csvQuestionFile());
    }

    @Bean
    @Qualifier("answerReaderService")
    public CsvReaderService<QuestionAnswer> csvAnswerReaderService() {
        return new CsvAnswerReaderService(properties.csvQuestionAnswersFile());
    }

    @Bean
    @Qualifier("correctAnswerReaderService")
    public CsvReaderService<QuestionAnswer> csvCorrectAnswerReaderService() {
        return new CsvAnswerReaderService(properties.csvCorrectAnswersFile());
    }

}
