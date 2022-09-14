package ru.otus.spring082022.Beloborodov02.domain;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private final String firstName;
    private final String lastName;

    private List<String> answers;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        answers = new ArrayList<>();
    }

    public String getAnswer(int index) {
        if (answers.size() < index || index < 0) return null;
        return answers.get(index);

    }

    public boolean addAnswer(String answer) {
        return answers.add(answer);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
