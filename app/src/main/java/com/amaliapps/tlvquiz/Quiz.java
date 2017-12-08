package com.amaliapps.tlvquiz;

import java.util.Map;

/**
 * Created by amaliam on 08/12/2017.
 */

class Quiz {
    private Map<Integer, Question> questionList;

    Quiz(Map<Integer, Question> questionList) {
        this.questionList = questionList;
        for (Question q : questionList.values()) {
            q.setScore(100 / questionList.size());
        }
    }

    Map<Integer, Question> getQuestionList() {
        return questionList;
    }

    int calculateScore() {
        int score = 0;
        for (Question q : questionList.values()) {
            if (q.getUserAnswer().equals(q.getCorrectAnswer())) {
                score += q.getScore();
            }
        }
        return score;
    }
}
