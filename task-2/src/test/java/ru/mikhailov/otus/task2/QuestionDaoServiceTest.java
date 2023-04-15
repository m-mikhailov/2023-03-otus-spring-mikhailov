package ru.mikhailov.otus.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mikhailov.otus.task2.domain.TestData;
import ru.mikhailov.otus.task2.domain.error.QuestionNotFoundException;
import ru.mikhailov.otus.task2.service.QuestionDaoServiceImpl;
import ru.mikhailov.otus.task2.service.reader.CsvQuestionReaderService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DisplayName("Question DAO service test")
@ExtendWith(MockitoExtension.class)
public class QuestionDaoServiceTest {

    @Mock
    private CsvQuestionReaderService questionReaderService;

    @BeforeEach
    public void init() {
        when(questionReaderService.readFile()).thenReturn(TestData.QUESTIONS_CSV_DATA);
    }

    @DisplayName("Should return all ids")
    @Test
    public void shouldReturnIds() {
        QuestionDaoServiceImpl service = new QuestionDaoServiceImpl(questionReaderService);
        var ids = service.getIds();
        assertEquals(3, ids.size());
    }

    @DisplayName("Should return question by id")
    @Test
    public void shouldReturnQuestionById() {
        QuestionDaoServiceImpl service = new QuestionDaoServiceImpl(questionReaderService);
        var actual = service.getById(2L);
        assertEquals(TestData.QUESTION_2, actual);
    }

    @DisplayName("Should throw QuestionNotFoundException")
    @Test
    public void shouldThrowException() {
        QuestionDaoServiceImpl service = new QuestionDaoServiceImpl(questionReaderService);
        Throwable ex = assertThrows(QuestionNotFoundException.class, () -> service.getById(4L));
        assertEquals("Question with ID 4 not found.", ex.getMessage());
    }

}
