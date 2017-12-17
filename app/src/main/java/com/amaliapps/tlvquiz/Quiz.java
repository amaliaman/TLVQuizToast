package com.amaliapps.tlvquiz;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amaliam on 08/12/2017.
 */

class Quiz implements Parcelable {
    private static final double MAX_SCORE = 100F;
    private Map<Integer, Question> questionList;
    private boolean isSuccess = false;

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

    boolean isSuccess() {
        return isSuccess;
    }

    /**
     * Calculate the score result of the quiz
     *
     * @return the result of the quiz
     */
    int calculateScore() {
        double score = 0;
        for (Question question : questionList.values()) {
            question.setIsCorrect(false);
            if (question.getUserAnswer().equals(question.getCorrectAnswer())) {
                score += question.getScore();
                question.setIsCorrect(true);
            }
        }
        isSuccess = false;
        if (score == MAX_SCORE) {
            isSuccess = true;
        }
        return (int) Math.ceil(score);
    }

    /*
     * Implementation of Parcelable interface
     */
    private Quiz(Parcel in) {
        questionList = new HashMap<Integer, Question>();
        in.readMap(questionList, Question.class.getClassLoader());
        isSuccess = in.readInt() == 1;
    }

    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeMap(questionList);
        dest.writeInt(isSuccess ? 1 : 0);
    }
}