package ru.otus.spring082022.Beloborodov03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InOutServiceImpl implements InOutService {


    private final LocalizedMessagesService localizedMessageService;

    private final StreamInOutService streamInOutService;

    @Autowired
    public InOutServiceImpl(LocalizedMessagesService localizedMessageService, StreamInOutService streamInOutService) {
        this.localizedMessageService = localizedMessageService;
        this.streamInOutService = streamInOutService;
    }


    // вывод локализованной строки без перевода каретки по шаблону
    @Override
    public void patternMessage(String patternID) {
        streamInOutService.outString(localizedMessageService.getLocalizedMessage(patternID));

    }

    // вывод  строки без перевода каретки
    @Override
    public void textMessage(String message) {
        streamInOutService.outString(message);
    }

    // вывод  строки c переводом каретки
    @Override
    public void textMessageWithCR(String message) {
        streamInOutService.outString(message + System.lineSeparator());
    }


    @Override
    public String enterStringWithPatternPrompt(String patternID) {
        streamInOutService.outString(localizedMessageService.getLocalizedMessage(patternID));
        return streamInOutService.inString();
    }


}
