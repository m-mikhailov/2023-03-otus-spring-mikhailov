package ru.mikhailov.otus.task1.service;

import ru.mikhailov.otus.task1.domain.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizServiceImpl implements QuizService {

    private final QuestionReaderService questionReaderService;

    private final IOService ioService;

    public QuizServiceImpl(QuestionReaderService questionReaderService, IOService ioService) {
        this.questionReaderService = questionReaderService;
        this.ioService = ioService;
    }

    @Override
    public void runQuiz() {
        var questions = questionReaderService.readQuestions();
        System.out.println("Quiz is starting...");
        var userAnswers = printQuestionsAndGetAnswers(questions);
        //TODO Check user answers
        System.out.println("Quiz has finished.");
    }

    private List<String> printQuestionsAndGetAnswers(List<Question> questions) {
        Scanner scanner = new Scanner(System.in);
        List<String> userAnswers = new ArrayList<>(questions.size());
        for (Question question : questions) {
            ioService.print(question);
            System.out.print(">> ");
            var userInput = scanner.nextLine();
            userAnswers.add(userInput);
        }
        return userAnswers;
    }



}
