package com.amaliapps.tlvquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);

        // get variables from main activity
        Intent intent = getIntent();
        int score = Integer.parseInt(intent.getStringExtra(MainActivity.EXTRA_SCORE));// why converted to string???
        Quiz q = intent.getParcelableExtra("aaa");

        TextView textView = findViewById(R.id.a);
        textView.setText(score + "");

        TextView textView2 = findViewById(R.id.b);
        textView2.setText(q.getQuestionList().get(1).getCorrectAnswer());
    }
}