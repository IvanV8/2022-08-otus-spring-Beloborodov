package ru.otus.spring082022.Beloborodov04.сommandlinerunners;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring082022.Beloborodov04.domain.Student;
import ru.otus.spring082022.Beloborodov04.events.EventsPublisher;
import ru.otus.spring082022.Beloborodov04.service.QuestionService;

// реализация команд оболочки spring shell
@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellCommands {

    private final EventsPublisher eventsPublisher;

    private final QuestionService questionService;

    private Student student = null;


    @ShellMethod(value = "Authorize", key = {"a", "auth"}, group = "Exam")
    public String auth(@ShellOption(help = "first name") String firstName, @ShellOption(help = "first name") String lastName) {
        student = new Student(firstName, lastName);
        return String.format("%s, you have been authorized for test.", student.getFullName());
    }


    @ShellMethod(value = "Test", key = {"t", "test"})
    @ShellMethodAvailability(value = "isAuthorized")
    public String test() {
        String score = questionService.doTest(student);
        eventsPublisher.publish(score);
        return "Test completed";
    }

    private Availability isAuthorized() {
        return student == null ? Availability.unavailable("Authorize before test (command 'auth'") : Availability.available();
    }
}


