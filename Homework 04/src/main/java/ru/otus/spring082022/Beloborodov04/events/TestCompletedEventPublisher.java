package ru.otus.spring082022.Beloborodov04.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestCompletedEventPublisher implements EventsPublisher {

    private final ApplicationEventPublisher publisher;

    @Override
    public void publish(String score) {
        publisher.publishEvent(new TestCompletedEvent(this, score));
    }
}
