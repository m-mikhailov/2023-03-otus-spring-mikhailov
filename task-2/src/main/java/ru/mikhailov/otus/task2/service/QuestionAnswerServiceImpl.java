package ru.mikhailov.otus.task2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task2.service.io.IOService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionAnswerServiceImpl implements QuestionAnswerService {

    private final QuestionAnswerConverter converter;

    private final IOService ioService;

    private final QuestionService questionService;

    @Override
    public List<Long> getQuestionIds() {
        return questionService.getIds();
    }

    @Override
    public void printQuestion(Long questionId) {
        var question = questionService.getById(questionId);
        ioService.printLine(converter.convertQuestionToString(question));
        ioService.printLine(converter.convertAnswersToString(question.answers()));
    }

}
