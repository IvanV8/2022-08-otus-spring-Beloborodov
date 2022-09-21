package ru.otus.spring082022.Beloborodov03.service;

public interface InOutService {


    public void PatternMessage(String patternID);

    public void TextMessage(String message);

    public void TextMessageWithCR(String message);

    String EnterStringWithPatternPrompt(String patternID);


}
