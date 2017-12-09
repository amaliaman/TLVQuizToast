package com.amaliapps.tlvquiz;

import android.util.Log;

import java.util.Map;

/**
 * Created by amaliam on 08/12/2017.
 */

class Quiz {
    private Map<Integer, Question> questionList;

    Quiz(Map<Integer, Question> questionList) {
        this.questionList = questionList;
        double score = 100F / questionList.size();
        for (Question q : questionList.values()) {
            q.setScore(score);
        }
    }

    Map<Integer, Question> getQuestionList() {
        return questionList;
    }

    int calculateScore() {
        double score = 0;
        for (Question q : questionList.values()) {
            if (q.getUserAnswer().equals(q.getCorrectAnswer())) {
                score += q.getScore();
            }
        }
        return (int) Math.ceil(score);
    }
}
