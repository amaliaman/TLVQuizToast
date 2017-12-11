package com.amaliapps.tlvquiz;

import java.util.Map;

/**
 * Created by amaliam on 08/12/2017.
 */

class Quiz {
    private static final double MAX_SCORE = 100F;
    private Map<Integer, Question> questionList;

    /**
     * -- Constructor --
     * When the Quiz is instantiated, every question in it gets a score which is
     * the maximum score (usually 100) divided by the number of questions
     *
     * @param questionList is a list of Question objects
     */
    Quiz(Map<Integer, Question> questionList) {
        this.questionList = questionList;
        double questionScore = MAX_SCORE / questionList.size();
        for (Question question : questionList.values()) {
            question.setScore(questionScore);
        }
    }

    Map<Integer, Question> getQuestionList() {
        return questionList;
    }

    /**
     * Calculate the score result of the quiz
     *
     * @return the result of the quiz
     */
    int calculateScore() {
        double score = 0;
        for (Question question : questionList.values()) {
            if (question.getUserAnswer().equals(question.getCorrectAnswer())) {
                score += question.getScore();
            }
        }
        return (int) Math.ceil(score);
    }
}
