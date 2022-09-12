package ru.otus.spring082022.Beloborodov02.appconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${questions.maxnumberofquestions}")
    private int numberOfQuestions;

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

}
