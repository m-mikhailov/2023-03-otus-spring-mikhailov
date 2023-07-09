package ru.mikhailov.otus.task8.repository;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan("ru.mikhailov.otus.task8.repository")
public abstract class AbstractRepositoryTest {
}
