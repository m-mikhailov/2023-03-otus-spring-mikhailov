package ru.mikhailov.otus.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.mikhailov.otus.task4.config.properties.ResourceProvider;
import ru.mikhailov.otus.task4.service.QuestionDaoService;
import ru.mikhailov.otus.task4.service.QuestionDaoServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Question DAO service tests")
@SpringBootTest(
        classes = QuestionDaoServiceImpl.class,
        properties = "spring.shell.interactive.enabled=false"
)
public class QuestionDaoServiceTests {

    @MockBean
    private ResourceProvider resourceProvider;

    @Autowired
    private QuestionDaoService daoService;

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
