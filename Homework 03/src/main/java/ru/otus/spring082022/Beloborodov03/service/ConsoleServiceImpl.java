package ru.otus.spring082022.Beloborodov03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring082022.Beloborodov03.repositary.IOProvider;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class ConsoleServiceImpl implements ConsoleService {
    private final PrintStream printStream;

    private final Scanner inStream;


    @Autowired
    public ConsoleServiceImpl(IOProvider ioProvider) {
        this.printStream = ioProvider.getOutStream();
        this.inStream = ioProvider.getInStream();
    }

    // вывод  строки
    @Override
    public void outString(String textMessageCode) {
        printStream.print(textMessageCode);
    }

    // ввод с клавиатуры строки с сообщением
    @Override
    public String inString() {
        return inStream.nextLine();
    }

}
