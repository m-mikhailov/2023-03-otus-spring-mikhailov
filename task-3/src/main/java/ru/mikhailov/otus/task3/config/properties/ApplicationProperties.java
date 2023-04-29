package ru.mikhailov.otus.task3.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@Data
@ConfigurationProperties("quiz")
public class ApplicationProperties implements ResourceProvider, QuizPropertiesProvider, LocaleProvider {

    private String fileName;

    private int minimalScore;

    private Locale locale;

}
