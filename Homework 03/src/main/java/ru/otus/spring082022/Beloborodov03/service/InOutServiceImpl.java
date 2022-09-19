package ru.otus.spring082022.Beloborodov03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring082022.Beloborodov03.appconfig.LocalizedMessages;
import ru.otus.spring082022.Beloborodov03.repositary.IOProvider;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class InOutServiceImpl implements InOutService {

    private final PrintStream printStream;

    private final Scanner inStream;
    private final LocalizedMessages localizedMessages;

    @Autowired
    public InOutServiceImpl(IOProvider ioProvider, LocalizedMessages localizedMessages) {
        this.printStream = ioProvider.getOutStream();
        this.inStream = ioProvider.getInStream();
        this.localizedMessages = localizedMessages;
    }


    // вывод локализованной строки без перевода каретки по шаблону (localized=true) или произвольной строки ((localized=false)
    @Override
    public void outString(boolean localized, String textMessageCode) {
        printStream.print(
                localized ? localizedMessages.getLocalizedMessage(textMessageCode) : textMessageCode);
    }

    // вывод локализованной строки с переводом   каретки по шаблону
    // (localized=true) или произвольной строки ((localized=false) в консоль
    @Override
    public void outStringn(boolean localized, String textMessageCode) {
        printStream.println(
                localized ? localizedMessages.getLocalizedMessage(textMessageCode) : textMessageCode);
    }


    @Override
    public String inStringWithPrompt(boolean localized, String textMessageCode) {
        printStream.print(
                localized ? localizedMessages.getLocalizedMessage(textMessageCode) : textMessageCode);
        return inStream.nextLine();
    }

}
