package com.amaliapps.tlvquiz;

/**
 * Created by amaliam on 08/12/2017.
 */

class Question {
    private int score;
    private String correctAnswer;
    private String userAnswer;

    public Question(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
