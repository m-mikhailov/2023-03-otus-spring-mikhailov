package ru.mikhailov.otus.task3.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mikhailov.otus.task3.config.properties.ApplicationProperties;
import ru.mikhailov.otus.task3.config.properties.LocaleProvider;
import ru.mikhailov.otus.task3.service.io.IOService;
import ru.mikhailov.otus.task3.service.io.IOStreamsService;

@Configuration
@EnableConfigurationProperties(ApplicationProperties.class)
public class ApplicationConfig {

    @Bean
    public IOService ioStreamsService(MessageSource messageSource, LocaleProvider localeProvider) {
        return new IOStreamsService(System.out, System.in, messageSource, localeProvider);
    }

}
