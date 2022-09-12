package ru.otus.spring082022.Beloborodov02.repositary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Component
public class IOProvider {
    @Value("#{T(java.lang.System).out}")
    private PrintStream outStream;

    @Value("#{T(java.lang.System).in}")
    private InputStream inStream;


    public PrintStream getOutStream() {
        return outStream;
    }

    public Scanner getInStream() {
        Scanner scanner = new Scanner(inStream);
        return scanner;
    }
}
