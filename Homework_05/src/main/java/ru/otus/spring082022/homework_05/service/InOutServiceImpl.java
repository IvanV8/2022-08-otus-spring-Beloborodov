package ru.otus.spring082022.homework_05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class InOutServiceImpl implements InOutService {

    private final PrintStream printStream;

    private final Scanner inStream;

    @Autowired
    public InOutServiceImpl(IOProvider ioProvider) {
        this.printStream = ioProvider.getOutStream();
        this.inStream = ioProvider.getInStream();
    }


    @Override
    public void outStringn(String textMessage) {
        printStream.println(textMessage);
    }

    @Override
    public String inStringWithPrompt(String prompt) {
        printStream.print(prompt);
        return inStream.nextLine();
    }

    @Override
    public long inLongWithPrompt(String prompt) {
        printStream.print(prompt);
        return inStream.nextLong();
    }

}
