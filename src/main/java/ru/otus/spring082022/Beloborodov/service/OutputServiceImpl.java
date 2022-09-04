package ru.otus.spring082022.Beloborodov.service;

import java.io.OutputStream;
import java.io.PrintStream;

public class OutputServiceImpl implements OutputService {

    PrintStream printStream;

    public OutputServiceImpl() {

    }

    @Override
    public void out(String textMessage) {
        printStream.println(textMessage);

    }

    @Override
    public void setOutputStream(OutputStream outputStream) {
        this.printStream = new PrintStream(outputStream);
    }
}
