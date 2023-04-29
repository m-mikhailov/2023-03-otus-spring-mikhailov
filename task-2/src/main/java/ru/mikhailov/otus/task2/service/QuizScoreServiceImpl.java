package ru.mikhailov.otus.task2.service;

import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task2.config.properties.QuizPropertiesProvider;
import ru.mikhailov.otus.task2.domain.Answer;
import ru.mikhailov.otus.task2.domain.Question;
import ru.mikhailov.otus.task2.domain.Student;
import ru.mikhailov.otus.task2.service.dao.CsvDaoService;
import ru.mikhailov.otus.task2.service.io.IOService;

import java.util.List;

@Service
public class QuizScoreServiceImpl implements QuizScoreService {

    private final QuizPropertiesProvider properties;

    private final List<Answer> correctAnswers;

    private final IOService ioService;

    public QuizScoreServiceImpl(
            CsvDaoService<Question> questionDao,
            QuizPropertiesProvider properties,
            IOService ioService
    ) {
        this.correctAnswers = questionDao.getAll()
                .stream()
                .flatMap(question -> question.correctAnswers().stream())
                .toList();
        this.properties = properties;
        this.ioService = ioService;
    }

    @Override
    public int calculate(List<Answer> answers) {
        var obtained = answers.stream()
                .filter(correctAnswers::contains)
                .count();
        return (int) obtained * 100 / correctAnswers.size();
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
