package ru.mikhailov.otus.task2.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task2.domain.QuestionAnswer;
import ru.mikhailov.otus.task2.service.io.IOService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuestionAnswerService qaService;

    private final QuizScoreService scoreService;

    private final StudentService studentService;

    private final IOService ioService;

    @Override
    public void start() {

        var student = studentService.introduce();

        var questionIds = qaService.getQuestionIds();
        ioService.printLine("Quiz is starting...");

        var studentAnswers = printQuestionsAndGetAnswers(questionIds);

        ioService.printLine("Quiz has finished.");

        var score = scoreService.calculate(studentAnswers);

        scoreService.printResult(student, score);

    }

    private List<QuestionAnswer> printQuestionsAndGetAnswers(List<Long> questionIds) {
        List<QuestionAnswer> userAnswers = new ArrayList<>(questionIds.size());

        for (Long id : questionIds) {
            qaService.printQuestion(id);
            var userInput = ioService.readLineWithPrompt("Enter answer:");
            userAnswers.add(parseUserInput(id, userInput));
        }
        return userAnswers;
    }

    private QuestionAnswer parseUserInput(Long questionId, String userInput) {
        var values = userInput.split(",|\s");
        return new QuestionAnswer(questionId, Arrays.stream(values).toList());
    }


}
