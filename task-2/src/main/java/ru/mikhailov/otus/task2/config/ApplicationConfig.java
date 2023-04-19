package ru.mikhailov.otus.task2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mikhailov.otus.task2.service.io.IOService;
import ru.mikhailov.otus.task2.service.io.IOStreamsService;

@Configuration
public class ApplicationConfig {

    @Bean
    public IOService ioStreamsService() {
        return new IOStreamsService(System.out, System.in);
    }

}
