package ru.mikhailov.otus.task2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mikhailov.otus.task2.domain.Question;
import ru.mikhailov.otus.task2.domain.error.QuestionNotFoundException;
import ru.mikhailov.otus.task2.service.dao.CsvDaoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final CsvDaoService<Question> questionDao;

    @Override
    public List<Long> getIds() {
        return questionDao.getAll().stream().map(Question::id).toList();
    }

    @Override
    public Question getById(Long id) {
        return questionDao.getAll().stream()
                .filter(question -> question.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new QuestionNotFoundException(id));
    }
}
