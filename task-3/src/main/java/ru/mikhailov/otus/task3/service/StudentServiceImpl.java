package ru.mikhailov.otus.task3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task3.domain.Student;
import ru.mikhailov.otus.task3.service.io.IOService;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final IOService ioService;

    @Override
    public Student introduce() {
        var studentFirstName = ioService.readLineWithLocalePrompt("student.first-name");
        var studentSecondName = ioService.readLineWithLocalePrompt("student.second-name");

        return new Student(studentFirstName, studentSecondName);
    }
}
