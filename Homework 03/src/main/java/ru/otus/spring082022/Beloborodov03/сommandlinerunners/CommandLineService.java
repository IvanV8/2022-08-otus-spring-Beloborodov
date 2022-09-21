package ru.otus.spring082022.Beloborodov03.сommandlinerunners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.spring082022.Beloborodov03.service.QuestionService;

import java.util.Arrays;


@Component
public class CommandLineService implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CommandLineService.class);

    private final QuestionService questionService;

    @Autowired
    public CommandLineService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void run(String... args) {
        logger.info("Приложение запустилось", Arrays.toString(args));
        questionService.doTest();

    }
}
