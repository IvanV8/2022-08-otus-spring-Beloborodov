package ru.otus.spring082022.Beloborodov03.service;

public interface InOutService {


    void outStringn(boolean localized, String textMessage);

    String inStringWithPrompt(boolean localized, String textMessageCode);


    void outString(boolean b, String s);
}
