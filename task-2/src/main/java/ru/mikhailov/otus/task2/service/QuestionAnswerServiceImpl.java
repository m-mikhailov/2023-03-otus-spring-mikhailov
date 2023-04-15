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

    private final QuestionDaoService questionDao;

    private final AnswerDaoService answerDao;

    @Override
    public List<Long> getQuestionIds() {
        return questionDao.getIds();
    }

    @Override
    public void printQuestion(Long questionId) {
        var question = questionDao.getById(questionId);
        var answerOpt = answerDao.getById(questionId);
        ioService.printLine(converter.convertQuestionToString(question));
        answerOpt.ifPresentOrElse(answer ->
                        ioService.printLine(converter.convertAnswerToString(answer)),
                () -> ioService.printLine("")
        );
    }

}
