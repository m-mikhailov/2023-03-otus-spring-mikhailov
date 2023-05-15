package ru.mikhailov.otus.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mikhailov.otus.task3.config.properties.ResourceProvider;
import ru.mikhailov.otus.task3.service.QuestionDaoServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Question DAO service tests")
@ExtendWith(MockitoExtension.class)
public class QuestionDaoServiceTests {

    @Mock
    private ResourceProvider resourceProvider;

    @InjectMocks
    private QuestionDaoServiceImpl daoService;

    @DisplayName("Should read file")
    @Test
    public void shouldReadFile() {
        Mockito.when(resourceProvider.getFileName()).thenReturn("questions.csv");

        var questions = daoService.getAll();
        assertEquals(5, questions.size());
    }

    @DisplayName("Should throw RuntimeException")
    @Test
    public void shouldThrowsException() {
        Mockito.when(resourceProvider.getFileName()).thenReturn("questions_1.csv");

        Throwable ex = assertThrows(RuntimeException.class, daoService::getAll);
        assertEquals("Resource reader has failed", ex.getMessage());
    }

}
