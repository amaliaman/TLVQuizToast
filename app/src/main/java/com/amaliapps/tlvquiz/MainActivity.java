package com.amaliapps.tlvquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createQuiz();
    }

    public void createQuiz() {
        Map<Integer, Question> questions = new HashMap<>();

        Question question1 = new Question(getString(R.string.question1_answer));
        questions.put(1, question1);

        Question question2 = new Question(getString(R.string.question2_answer));
        questions.put(2, question2);

        Question question3 = new Question(getString(R.string.question3_answer));
        questions.put(3, question3);

        /*Question question4 = new Question(20, "1909");
        questions.put(4, question4);

        Question question5 = new Question(20, "1909");
        questions.put(5, question5);*/

        quiz = new Quiz(questions);
    }

    /**
     * When the Submit button is clicked, calculate the score and display it in a toast
     *
     * @param view context
     */
    public void submitQuiz(View view) {
        setAnswers();
        int score = quiz.calculateScore();
        /*
        replace with double
         */
        if (score > 100) {
            score = 100;
        } else if (score > 97) {
            score = 100;
        }

        String message = ".\nFeel free to try again!";
        if (score == 100) {
            message = ".\nHurray!";
        }
        message = getString(R.string.toast, score) + message;
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void setAnswers() {
        Map<Integer, Question> questionList = quiz.getQuestionList();

        // get answer 1
        String answer1 = "";
        RadioButton radioIreland = findViewById(R.id.radio_ireland);
        RadioButton radioIsrael = findViewById(R.id.radio_israel);
        RadioButton radioIsle = findViewById(R.id.radio_isle);
        if (radioIreland.isChecked()) {
            answer1 = String.valueOf(radioIreland.getText());
        } else if (radioIsrael.isChecked()) {
            answer1 = String.valueOf(radioIsrael.getText());
        } else if (radioIsle.isChecked()) {
            answer1 = String.valueOf(radioIsle.getText());
        }
        questionList.get(1).setUserAnswer(answer1);

        // get answer 2
        EditText nameTextBox = findViewById(R.id.question_year_input);
        String answer2 = nameTextBox.getText().toString();
        questionList.get(2).setUserAnswer(answer2);

        // get answer 3
        String answer3 = "";
        RadioButton radioMediterranean = findViewById(R.id.radio_mediterranean);
        RadioButton radioDead = findViewById(R.id.radio_dead);
        RadioButton radioGalillee = findViewById(R.id.radio_galillee);
        if (radioMediterranean.isChecked()) {
            answer3 = String.valueOf(radioMediterranean.getText());
        } else if (radioDead.isChecked()) {
            answer3 = String.valueOf(radioDead.getText());
        } else if (radioGalillee.isChecked()) {
            answer3 = String.valueOf(radioGalillee.getText());
        }
        questionList.get(3).setUserAnswer(answer3);
    }
}
