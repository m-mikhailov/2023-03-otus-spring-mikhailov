package ru.mikhailov.otus.task11.config;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.reactivestreams.client.MongoClient;
import io.mongock.driver.mongodb.reactive.driver.MongoReactiveDriver;
import io.mongock.runner.springboot.EnableMongock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@EnableMongock
@Configuration
public class MongockConfig {

    @Bean
    public MongoReactiveDriver mongoReactiveDriver(MongoClient mongoClient) {
        var driver = MongoReactiveDriver.withDefaultLock(mongoClient, "testdb");
        driver.setWriteConcern(WriteConcern.MAJORITY.withJournal(false).withWTimeout(1000, TimeUnit.SECONDS));
        driver.setReadConcern(ReadConcern.MAJORITY);
        driver.setReadPreference(ReadPreference.primary());
        driver.disableTransaction();
        return driver;
    }
}
