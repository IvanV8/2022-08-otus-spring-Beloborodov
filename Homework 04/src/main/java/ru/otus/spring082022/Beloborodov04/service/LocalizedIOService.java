package ru.otus.spring082022.Beloborodov04.service;

public interface LocalizedIOService {


    void patternMessage(String patternID);

    void textMessage(String message);

    void textMessageWithCR(String message);

    String enterStringWithPatternPrompt(String patternID);


}
