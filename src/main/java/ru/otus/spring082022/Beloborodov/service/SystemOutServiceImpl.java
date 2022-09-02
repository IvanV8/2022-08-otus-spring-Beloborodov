package ru.otus.spring082022.Beloborodov.service;

import java.io.OutputStream;
import java.io.PrintStream;

public class SystemOutServiceImpl implements SystemOutService {

    private PrintStream console;

    public SystemOutServiceImpl(OutputStream out) {
        this.console = new PrintStream(out);
    }

    @Override
    public void SystemOut(OutputStream out) {
        this.console = new PrintStream(out);

    }

    @Override
    public void Out(String textMessage) {
        console.println(textMessage);

    }
}
