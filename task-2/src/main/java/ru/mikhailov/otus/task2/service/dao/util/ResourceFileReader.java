package ru.mikhailov.otus.task2.service.dao.util;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;

public class ResourceFileReader {

    public static <T> List<T> read(String fileName, Function<BufferedReader, List<T>> func) {
        ClassPathResource resource = new ClassPathResource(fileName);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream()))
        ) {
            return func.apply(reader);
        } catch (IOException e) {
            throw new RuntimeException("Resource reader has failed", e);
        }
    }

}
