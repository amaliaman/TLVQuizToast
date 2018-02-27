package com.amaliapps.tlvquiztoast;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by amaliam on 08/12/2017.
 */

class Question implements Parcelable {
    private double score;
    private String correctAnswer;
    private String userAnswer;
    private boolean isCorrect;

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

    boolean isCorrect() {
        return isCorrect;
    }

    void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    /*
     * Implementation of Parcelable interface
     */
    private Question(Parcel in) {
        score = in.readDouble();
        correctAnswer = in.readString();
        userAnswer = in.readString();
        isCorrect = in.readInt() == 1;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(score);
        dest.writeString(correctAnswer);
        dest.writeString(userAnswer);
        dest.writeInt((isCorrect ? 1 : 0));
    }
}
