package ru.mikhailov.otus.task3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task3.config.properties.QuizPropertiesProvider;
import ru.mikhailov.otus.task3.domain.Answer;
import ru.mikhailov.otus.task3.domain.Question;
import ru.mikhailov.otus.task3.domain.Student;
import ru.mikhailov.otus.task3.service.io.IOService;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizScoreServiceImpl implements QuizScoreService {

    private final QuizPropertiesProvider propertiesProvider;

    private final QuestionDaoService daoService;

    private final IOService ioService;

    private final LocalizedService localizedService;

    @Override
    public int calculate(List<Answer> answers) {
        var correctAnswers = getCorrectAnswers();

        var obtained = answers.stream()
                .filter(correctAnswers::contains)
                .count();
        return (int) obtained * 100 / correctAnswers.size();
    }

    private List<Answer> getCorrectAnswers() {
        return daoService.getAll()
                .stream()
                .map(Question::correctAnswers)
                .flatMap(Collection::stream)
                .toList();
    }

    @Override
    public void printResult(Student student, int score) {
        if (score > propertiesProvider.getMinimalScore()) {
            ioService.printLine(
                    localizedService.getMessage("result.good", student.firstName(), student.secondName(), score)
            );
        } else {
            ioService.printLine(
                    localizedService.getMessage("result.bad", propertiesProvider.getMinimalScore(), score)
            );
        }
    }

}
