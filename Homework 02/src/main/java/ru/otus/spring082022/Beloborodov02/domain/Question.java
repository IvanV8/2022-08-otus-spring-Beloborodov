package ru.otus.spring082022.Beloborodov02.domain;

public class Question {
    private String questionText;
    private String answerText;


    public Question(String questionText) {
        this.questionText = questionText;

    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public String getQuestionText() {
        return questionText;
    }
}
