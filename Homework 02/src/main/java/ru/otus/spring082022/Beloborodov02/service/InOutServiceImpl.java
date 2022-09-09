package ru.otus.spring082022.Beloborodov02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring082022.Beloborodov02.appconfig.AppConfig;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class InOutServiceImpl implements InOutService {
    private final PrintStream printStream;
    private final Scanner inStream;
    private final AppConfig appCofig;

    @Autowired
    public InOutServiceImpl(AppConfig appConfig) {
        this.appCofig = appConfig;
        printStream = appConfig.getOutStream();
        inStream = new Scanner(appCofig.getInStream());
    }

    @Override
    public void outString(String textMessage) {
        printStream.print(textMessage);

    }

    @Override
    public void outStringn(String textMessage) {
        printStream.println(textMessage);
    }

    @Override
    public String inString() {
        return inStream.nextLine();
    }

}
