package com.amaliapps.tlvquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayResultActivity extends AppCompatActivity {

    private final static String SUCCESS_SIGN = "✔";
    private final static String FAIL_SIGN = "✘";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);

        // get variables from main activity
        Intent intent = getIntent();
        int score = intent.getIntExtra(MainActivity.EXTRA_SCORE, 0);
        Quiz quiz = intent.getParcelableExtra(MainActivity.EXTRA_QUIZ);

        // set header TextView(s)
        TextView scoreTextView = findViewById(R.id.score_text_view);
        scoreTextView.setText(getString(R.string.score_message, score));

        TextView messageTextView = findViewById(R.id.message_text_view);
        int messageStringResource = (quiz.isSuccess() ? (R.string.pass_message) : (R.string.fail_message));
        messageTextView.setText(getString(messageStringResource));

        // set question TextView(s)
        populateUserAnswerMessages(quiz);
    }

    private void populateUserAnswerMessages(Quiz quiz) {
        // question 1
        Question question1 = quiz.getQuestionList().get(1);
        TextView userAnswer1 = findViewById(R.id.user_answer_1);
        userAnswer1.setText(getUserMessage(question1));

        // question 2
        Question question2 = quiz.getQuestionList().get(2);
        TextView userAnswer2 = findViewById(R.id.user_answer_2);
        userAnswer2.setText(getUserMessage(question2));

        // question 3
        Question question3 = quiz.getQuestionList().get(3);
        TextView userAnswer3 = findViewById(R.id.user_answer_3);
        userAnswer3.setText(getUserMessage(question3));

        // question 4
        Question question4 = quiz.getQuestionList().get(4);
        TextView userAnswer4 = findViewById(R.id.user_answer_4);
        userAnswer4.setText(getUserMessage(question4));

        // question 5
        Question question5 = quiz.getQuestionList().get(5);
        TextView userAnswer5 = findViewById(R.id.user_answer_5);
        userAnswer5.setText(getUserMessage(question5));
    }

    private String getUserMessage(Question question) {
        return getString(R.string.your_answer, question.getUserAnswer()) +
                " " + (question.isCorrect() ? SUCCESS_SIGN : FAIL_SIGN);
    }
}