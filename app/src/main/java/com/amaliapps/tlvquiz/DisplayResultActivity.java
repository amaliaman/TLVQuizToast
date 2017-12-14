package com.amaliapps.tlvquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayResultActivity extends AppCompatActivity {

    private final static String SUCCESS_SIGN = " ✔";
    private final static String FAIL_SIGN = " ✘";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);

        // get variables from main activity
        Intent intent = getIntent();
        int score = intent.getIntExtra(MainActivity.EXTRA_SCORE, 0);
        Quiz quiz = intent.getParcelableExtra(MainActivity.EXTRA_QUIZ);

        TextView scoreTextView = findViewById(R.id.score_text_view);
        scoreTextView.setText(getString(R.string.score_message, score));

        TextView messageTextView = findViewById(R.id.message_text_view);
        int messageStringResource = (quiz.isSuccess() ? (R.string.pass_message) : (R.string.fail_message));
        messageTextView.setText(getString(messageStringResource));

        // question 1
        Question question1 = quiz.getQuestionList().get(1);
        TextView userAnswer1 = findViewById(R.id.user_answer_1);
        userAnswer1.setText(getString(R.string.your_answer, question1.getUserAnswer()));
        userAnswer1.append(question1.isCorrect() ? SUCCESS_SIGN : FAIL_SIGN);

        // question 2
        Question question2 = quiz.getQuestionList().get(2);
        TextView userAnswer2 = findViewById(R.id.user_answer_2);
        userAnswer2.setText(getString(R.string.your_answer, question2.getUserAnswer()));
        userAnswer2.append(question2.isCorrect() ? SUCCESS_SIGN : FAIL_SIGN);
    }
}