package ru.otus.spring082022.Beloborodov04.service;

public interface InOutService {


    String patternMessage(String patternID);

    String textMessage(String message);

    String textMessageWithCR(String message);

    String enterStringWithPatternPrompt(String patternID);


}
