package ru.mikhailov.otus.task2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task2.domain.Student;
import ru.mikhailov.otus.task2.service.io.IOService;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final IOService ioService;

    @Override
    public Student introduce() {
        var studentFirstName = ioService.readLineWithPrompt("Please enter your first name:");
        var studentSecondName = ioService.readLineWithPrompt("Please enter your second name:");

        return new Student(studentFirstName, studentSecondName);
    }
}
