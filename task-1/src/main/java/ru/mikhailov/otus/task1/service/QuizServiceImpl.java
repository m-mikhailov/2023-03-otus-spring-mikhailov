package ru.mikhailov.otus.task1.service;

import ru.mikhailov.otus.task1.domain.Question;

import java.util.Scanner;

public class QuizServiceImpl implements QuizService {

    private final QuestionReaderService questionReaderService;

    public QuizServiceImpl(QuestionReaderService questionReaderService) {
        this.questionReaderService = questionReaderService;
    }

    @Override
    public void runQuiz() {
        var questions = questionReaderService.readQuestions();
        System.out.println("Quiz is starting...");
        Scanner scanner = new Scanner(System.in);
        for (Question question : questions) {
            question.print();
            System.out.print(">> ");
            var userInput = scanner.nextLine();
            //Check answer
        }
        System.out.println("Quiz has finished.");
    }



}
