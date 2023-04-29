package ru.mikhailov.otus.task3.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task3.domain.Answer;
import ru.mikhailov.otus.task3.domain.Question;
import ru.mikhailov.otus.task3.service.io.IOService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuestionService questionService;

    private final QuizScoreService scoreService;

    private final StudentService studentService;

    private final IOService ioService;

    @Override
    public void start() {

        var student = studentService.introduce();

        ioService.printLocaleLine("quiz.start");

        var studentAnswers = printQuestionsAndGetAnswers();

        ioService.printLocaleLine("quiz.end");

        var score = scoreService.calculate(studentAnswers);

        scoreService.printResult(student, score);

    }

    private List<Answer> printQuestionsAndGetAnswers() {
        var questions = questionService.getAll();
        var userAnswers = new ArrayList<Answer>();
        for (Question question : questions) {
            questionService.print(question);
            var userInput = ioService.readLineWithLocalePrompt("enter.answer");
            userAnswers.addAll(parseUserInput(question.id(), userInput));
        }
        return userAnswers;
    }

    private List<Answer> parseUserInput(Long questionId, String userInput) {
        return Arrays.stream(userInput.split(",|\s"))
                .map(answer -> new Answer(questionId, answer))
                .toList();
    }


}
