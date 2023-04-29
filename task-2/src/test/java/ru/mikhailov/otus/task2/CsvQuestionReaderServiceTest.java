package ru.mikhailov.otus.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mikhailov.otus.task2.config.properties.ResourceProvider;
import ru.mikhailov.otus.task2.service.dao.CsvQuestionDaoService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("CSV Question reader service test")
public class CsvQuestionReaderServiceTest {

    @DisplayName("Should read file")
    @Test
    public void shouldReadFile() {
        var resourceProvider = new ResourceProvider() {
            @Override
            public String fileName() {
                return "questions.csv";
            }
        };

        CsvQuestionDaoService service = new CsvQuestionDaoService(resourceProvider);
        var questions = service.getAll();
        assertEquals(5, questions.size());
    }

    @DisplayName("Should throw RuntimeException")
    @Test
    public void shouldThrowsException() {
        var resourceProvider = new ResourceProvider() {
            @Override
            public String fileName() {
                return "questions_1.csv";
            }
        };

        CsvQuestionDaoService service = new CsvQuestionDaoService(resourceProvider);
        Throwable ex = assertThrows(RuntimeException.class, service::getAll);
        assertEquals("Resource reader has failed", ex.getMessage());
    }

}
