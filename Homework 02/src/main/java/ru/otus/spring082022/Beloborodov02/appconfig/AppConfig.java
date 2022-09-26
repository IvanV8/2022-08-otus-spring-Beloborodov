package ru.otus.spring082022.Beloborodov02.appconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class AppConfig {

    private final int numberOfQuestions;

    public AppConfig(@Value("${questions.maxnumberofquestions:5}") int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }


}
