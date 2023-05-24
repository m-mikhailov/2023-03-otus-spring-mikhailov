package ru.mikhailov.otus.task4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task4.domain.Student;
import ru.mikhailov.otus.task4.service.io.IOService;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final IOService ioService;

    private final LocalizedService localizedService;

    @Override
    public Student introduce() {
        var studentFirstName = ioService.readLineWithPrompt(
                localizedService.getMessage("student.first-name")
        );
        var studentSecondName = ioService.readLineWithPrompt(
                localizedService.getMessage("student.second-name")
        );

        return new Student(studentFirstName, studentSecondName);
    }
}
