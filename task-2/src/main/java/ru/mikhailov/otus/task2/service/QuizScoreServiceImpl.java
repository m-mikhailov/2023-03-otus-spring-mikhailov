package ru.mikhailov.otus.task2.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task2.config.properties.ApplicationProperties;
import ru.mikhailov.otus.task2.domain.QuestionAnswer;
import ru.mikhailov.otus.task2.domain.Student;
import ru.mikhailov.otus.task2.service.io.IOService;
import ru.mikhailov.otus.task2.service.reader.CsvReaderService;

import java.util.List;

@Service
public class QuizScoreServiceImpl implements QuizScoreService {

    private final ApplicationProperties properties;

    private final List<QuestionAnswer> correctAnswers;

    private final IOService ioService;

    public QuizScoreServiceImpl(
            @Qualifier("correctAnswerReaderService") CsvReaderService<QuestionAnswer> reader,
            ApplicationProperties properties,
            IOService ioService
    ) {
        this.correctAnswers = reader.readFile();
        this.properties = properties;
        this.ioService = ioService;
    }

    @Override
    public int calculate(List<QuestionAnswer> answers) {
        var obtained = answers.stream()
                .filter(this::containsAnswer)
                .count();
        return (int) obtained * 100 / correctAnswers.size();
    }

    private boolean containsAnswer(QuestionAnswer answer) {
        return correctAnswers.stream()
                .filter(ca -> ca.questionId().equals(answer.questionId()))
                .findFirst()
                .filter(ca -> ca.answers().containsAll(answer.answers()))
                .isPresent();
    }

    @Override
    public void printResult(Student student, int score) {
        if (score > properties.minimalScore()) {
            ioService.printLine("Congratulations, %s %s! You have passed the quiz. Your score is %s."
                    .formatted(student.firstName(), student.secondName(), score)
            );
        } else {
            ioService.printLine("Unfortunately, you have not passed the quiz. Minimal score is %s. Your score is %s"
                    .formatted(properties.minimalScore(), score)
            );
        }
    }

}
