package ru.mikhailov.otus.task4.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.mikhailov.otus.task4.service.QuizService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationQuizCommands {

    private final QuizService quiz;

    @ShellMethod(value = "Start quiz", key = {"start"})
    public void startQuiz() {
        quiz.start();
    }

}
