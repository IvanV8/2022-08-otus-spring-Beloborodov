package ru.otus.spring082022.Beloborodov04.domain;


// класс-домен для хранения вопросов
public class Question {
    private final String questionText;
    private final String rightAnswerText;


    public Question(String questionText, String rightAnswerText) {
        this.questionText = questionText;
        this.rightAnswerText = rightAnswerText;

    }

    public String getRightAnswerText() {
        return rightAnswerText;
    }


    public String getQuestionText() {
        return questionText;
    }

    public boolean isRightAnswer(String answerText) {
        if (rightAnswerText == null || answerText == null) return false;
        return answerText.equalsIgnoreCase(rightAnswerText);
    }

}
