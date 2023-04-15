package ru.mikhailov.otus.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mikhailov.otus.task2.service.reader.CsvQuestionReaderService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("CSV Question reader service test")
public class CsvQuestionReaderServiceTest {

    @DisplayName("Should read file")
    @Test
    public void shouldReadFile() {
        CsvQuestionReaderService service = new CsvQuestionReaderService("questions.csv");
        var questions = service.readFile();
        assertEquals(5, questions.size());
    }

    @DisplayName("Should throw RuntimeException")
    @Test
    public void shouldThrowsException() {
        CsvQuestionReaderService service = new CsvQuestionReaderService("questions_1.csv");
        Throwable ex = assertThrows(RuntimeException.class, service::readFile);
        assertEquals("Resource reader has failed", ex.getMessage());
    }

}
