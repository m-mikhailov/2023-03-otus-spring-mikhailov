package ru.mikhailov.otus.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mikhailov.otus.task1.domain.Question;
import ru.mikhailov.otus.task1.service.QuestionCSVReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("QuestionCSVReader test")
public class QuestionCSVReaderTest {

    @DisplayName("Read file correct")
    @Test
    void shouldReadFileCorrect() {
        QuestionCSVReader reader = new QuestionCSVReader("questions.csv");
        List<Question> questions = reader.readQuestions();
        assertEquals(4, questions.size());
    }

    @DisplayName("File not found")
    @Test
    void shouldThrowsFileNotFound() {
        QuestionCSVReader reader = new QuestionCSVReader("questions_1.csv");
        Throwable ex = assertThrows(RuntimeException.class, () -> reader.readQuestions());
        assertEquals("Resource reader has failed", ex.getMessage());
    }

}
