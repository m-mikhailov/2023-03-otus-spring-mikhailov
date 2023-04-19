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
public class ApplicationProperties implements ResourceProvider, QuizPropertiesProvider {

    @Value("${csv.questions.file-name}")
    private String fileName;

    @Value("${quiz.minimal-score}")
    private int minimalScore;

}
