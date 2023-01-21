package ru.otus.spring082022.homework08.repository;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.spring082022.homework08.config", "ru.otus.spring082022.homework08.repositories"})
public abstract class AbstractRepositoryTest {
}
