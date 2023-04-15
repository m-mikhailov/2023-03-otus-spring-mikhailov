package ru.mikhailov.otus.task2.config.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Getter
@Setter
@Accessors(fluent = true)
@PropertySource("classpath:application.properties")
public class ApplicationProperties {

    @Value("${csv.questions.file-name}")
    private String csvQuestionFile;

    @Value("${csv.question-answers.file-name}")
    private String csvQuestionAnswersFile;

    @Value("${csv.correct-answers.file-name}")
    private String csvCorrectAnswersFile;

    @Value("${minimal-score}")
    private int minimalScore;

}
