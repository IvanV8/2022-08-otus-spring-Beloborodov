package ru.otus.spring082022.Beloborodov03.сommandlinerunners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.spring082022.Beloborodov03.service.QuestionService;

import java.util.Arrays;

// Класс для реализации консольного ввода вывода

@Component
public class ConsoleService implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleService.class);

    @Autowired
    QuestionService questionService;

    @Override
    public void run(String... args) {
        logger.info("Приложение запустилось", Arrays.toString(args));
        questionService.doTest();

    }
}
