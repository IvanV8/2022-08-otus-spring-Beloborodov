package ru.otus.spring082022.Beloborodov.service;

import java.io.OutputStream;
import java.io.PrintStream;

public interface SystemOutService {
    void SystemOut(OutputStream console);

    void Out(String textMessage);
}
