package ru.mikhailov.otus.task4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task4.domain.Question;
import ru.mikhailov.otus.task4.service.io.IOService;
import ru.mikhailov.otus.task4.utils.QuestionConverter;


import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDaoService daoService;

    private final QuestionConverter converter;

    private final IOService ioService;


    @Override
    public List<Question> getAll() {
        return daoService.getAll();
    }

    @Override
    public void print(Question question) {
        ioService.printLine(converter.convertQuestionToString(question));
    }
}
