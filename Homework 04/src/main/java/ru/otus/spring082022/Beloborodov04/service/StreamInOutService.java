package ru.otus.spring082022.Beloborodov04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring082022.Beloborodov04.repositary.IOProvider;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class StreamInOutService implements IOService {
    private final PrintStream printStream;

    private final Scanner inStream;


    @Autowired
    public StreamInOutService(IOProvider ioProvider) {
        this.printStream = ioProvider.getOutStream();
        this.inStream = ioProvider.getInStream();
    }

    // вывод  строки
    @Override
    public String outString(String textMessageCode) {
        printStream.print(textMessageCode);
        return textMessageCode;
    }

    // ввод с клавиатуры строки с сообщением
    @Override
    public String inString() {
        return inStream.nextLine();
    }

}
