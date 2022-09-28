package ru.otus.spring082022.Beloborodov04.events;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


// класс события при завершении теста
public class TestCompletedEvent extends ApplicationEvent {
    @Getter
    @Setter
    private final String score;

    public TestCompletedEvent(Object source, String score) {
        super(source);
        this.score = score;
    }
}
