package ru.otus.spring082022.homework06.service;

public interface InOutService {


    void outStringn(String textMessage);

    String inStringWithPrompt(String prompt);


    long inLongWithPrompt(String s);
}
