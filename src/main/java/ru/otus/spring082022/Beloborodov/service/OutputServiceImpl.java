package ru.otus.spring082022.Beloborodov.service;

import java.io.PrintStream;

public class OutputServiceImpl implements OutputService {
    private final PrintStream printStream;

    public OutputServiceImpl(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void out(String textMessage) {
        printStream.println(textMessage);

    }

}
