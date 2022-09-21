package ru.otus.spring082022.Beloborodov03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InOutServiceImpl implements InOutService {


    private final LocalizedMessagesServiceImpl localizedMessageService;

    private final ConsoleService consoleService;

    @Autowired
    public InOutServiceImpl(LocalizedMessagesServiceImpl localizedMessageService, ConsoleService consoleService) {
        this.localizedMessageService = localizedMessageService;
        this.consoleService = consoleService;
    }


    // вывод локализованной строки без перевода каретки по шаблону
    @Override
    public void PatternMessage(String patternID) {
        consoleService.outString(localizedMessageService.getLocalizedMessage(patternID));

    }

    // вывод  строки без перевода каретки
    @Override
    public void TextMessage(String message) {
        consoleService.outString(message);
    }

    // вывод  строки c переводом каретки
    @Override
    public void TextMessageWithCR(String message) {
        consoleService.outString(message + System.lineSeparator());
    }


    @Override
    public String EnterStringWithPatternPrompt(String patternID) {
        consoleService.outString(localizedMessageService.getLocalizedMessage(patternID));
        return consoleService.inString();
    }


}
