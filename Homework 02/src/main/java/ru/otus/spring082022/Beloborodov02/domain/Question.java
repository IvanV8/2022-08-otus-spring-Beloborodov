package ru.otus.spring082022.Beloborodov02.domain;

public class Question {
    private final String questionText;
    private final String rightAnswerText;
    private String answerText;


    public Question(String questionText, String rightAnswerText) {
        this.questionText = questionText;
        this.rightAnswerText = rightAnswerText;

    }

    public String getRightAnswerText() {
        return rightAnswerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public boolean isRightAnswer() {
        if (rightAnswerText == null || answerText == null) return false;
        return answerText.equalsIgnoreCase(rightAnswerText);
    }

}
