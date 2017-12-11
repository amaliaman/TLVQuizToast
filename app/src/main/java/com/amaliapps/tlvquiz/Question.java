package com.amaliapps.tlvquiz;

/**
 * Created by amaliam on 08/12/2017.
 */

class Question {
    private double score;
    private String correctAnswer;
    private String userAnswer;

    Question(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    double getScore() {
        return score;
    }

    void setScore(double score) {
        this.score = score;
    }

    String getCorrectAnswer() {
        return correctAnswer;
    }

    String getUserAnswer() {
        return userAnswer;
    }

    void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
