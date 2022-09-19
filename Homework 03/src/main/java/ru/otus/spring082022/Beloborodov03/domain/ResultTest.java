package ru.otus.spring082022.Beloborodov03.domain;

import java.util.ArrayList;
import java.util.List;


// класс -домен для результатов тестов студентов
public class ResultTest {
    Student student;
    List<String> answers;

    public ResultTest(Student student) {
        this.student = student;
        answers = new ArrayList<>();
    }

    public String getAnswer(int index) {
        if (answers.size() < index || index < 0) return null;
        return answers.get(index);

    }

    public boolean addAnswer(String answer) {
        return answers.add(answer);
    }
}
