package ru.otus.spring082022.Beloborodov04.domain;

import java.util.ArrayList;
import java.util.List;


// класс -домен для результатов тестов студентов
public class ResultTest {
    private final Student student;
    private List<String> answers;

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

    public String score(List<Question> questions) {
        int rightAnswers = 0;
        for (int i = 0; i < questions.size(); i++) {
            String answer = getAnswer(i);
            if (answer != null) {
                if (questions.get(i).isRightAnswer(answer)) {
                    rightAnswers++;
                }
            }
        }
        return String.format(" %d/%d", rightAnswers, questions.size());

    }

}
