package ru.otus.spring082022.Beloborodov.domain;

public class Question {
    private String questionText;
    private String answerText;

    public Question(String questionText, String answerText) {
        this.questionText = questionText;
        this.answerText = answerText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public String getQuestionText() {
        return questionText;
    }
}
