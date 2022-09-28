package ru.otus.spring082022.Beloborodov04.events;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ru.otus.spring082022.Beloborodov04.service.InOutService;


// класс обработчик события
@Component
@RequiredArgsConstructor
public class TestCompletedRespond implements ApplicationListener<TestCompletedEvent> {

    private final InOutService inOutService;

    @SneakyThrows
    @Override
    public void onApplicationEvent(TestCompletedEvent testCompletedEvent) {
        Thread.sleep(1000);
        inOutService.textMessageWithCR("Test completed.");
        inOutService.textMessageWithCR(String.format("Score is: %s", testCompletedEvent.getScore()));

    }
}
