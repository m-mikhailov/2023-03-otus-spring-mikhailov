package ru.mikhailov.otus.task4.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mikhailov.otus.task4.config.properties.ApplicationProperties;
import ru.mikhailov.otus.task4.service.io.IOService;
import ru.mikhailov.otus.task4.service.io.IOStreamsService;


@Configuration
@EnableConfigurationProperties(ApplicationProperties.class)
public class ApplicationConfig {

    @Bean
    public IOService ioStreamsService() {
        return new IOStreamsService(System.out, System.in);
    }

}
