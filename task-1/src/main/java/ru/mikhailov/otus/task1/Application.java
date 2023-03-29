package ru.mikhailov.otus.task1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.mikhailov.otus.task1.service.QuizService;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        QuizService quizService = context.getBean(QuizService.class);
        quizService.runQuiz();
    }

}
