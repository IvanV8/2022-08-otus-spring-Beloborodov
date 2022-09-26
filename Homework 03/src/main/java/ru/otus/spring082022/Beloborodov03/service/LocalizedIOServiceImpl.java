package ru.otus.spring082022.Beloborodov03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalizedIOServiceImpl implements LocalizedIOService {


    private final LocalizedMessagesService localizedMessageService;

    private final IOService IOService;

    @Autowired
    public LocalizedIOServiceImpl(LocalizedMessagesService localizedMessageService, IOService IOService) {
        this.localizedMessageService = localizedMessageService;
        this.IOService = IOService;
    }


    // вывод локализованной строки без перевода каретки по шаблону
    @Override
    public void patternMessage(String patternID) {
        IOService.outString(localizedMessageService.getLocalizedMessage(patternID));

    }

    // вывод  строки без перевода каретки
    @Override
    public void textMessage(String message) {
        IOService.outString(message);
    }

    // вывод  строки c переводом каретки
    @Override
    public void textMessageWithCR(String message) {
        IOService.outString(message + System.lineSeparator());
    }


    @Override
    public String enterStringWithPatternPrompt(String patternID) {
        IOService.outString(localizedMessageService.getLocalizedMessage(patternID));
        return IOService.inString();
    }


}
