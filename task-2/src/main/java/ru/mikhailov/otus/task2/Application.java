package ru.mikhailov.otus.task2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.mikhailov.otus.task2.service.QuizService;
import ru.mikhailov.otus.task2.service.QuizServiceImpl;

@ComponentScan
@Configuration
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        QuizService service = context.getBean(QuizServiceImpl.class);
        service.start();
    }

}
