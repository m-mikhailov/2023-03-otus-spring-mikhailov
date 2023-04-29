package ru.mikhailov.otus.task3.cmdrunner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.mikhailov.otus.task3.service.QuizService;

@Component
@RequiredArgsConstructor
public class ApplicationRunner implements CommandLineRunner {

    private final QuizService quiz;

    @Override
    public void run(String... args) throws Exception {
        quiz.start();
    }
}
