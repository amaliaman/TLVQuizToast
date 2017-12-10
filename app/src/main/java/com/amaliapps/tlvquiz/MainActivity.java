package com.amaliapps.tlvquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
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

        Question question4 = new Question(getString(R.string.question4_correct1) + "|" + getString(R.string.question4_correct2));
        questions.put(4, question4);

        Question question5 = new Question(getString(R.string.question5_answer));
        questions.put(5, question5);

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

        String message = ".\n" + getString(R.string.toast_fail);
        if (score == 100) {
            message = ".\n" + getString(R.string.toast_pass);
        }
        message = getString(R.string.toast, score) + message;
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void resetQuiz(View view){

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
        RadioButton radioGalilee = findViewById(R.id.radio_galilee);
        if (radioMediterranean.isChecked()) {
            answer3 = String.valueOf(radioMediterranean.getText());
        } else if (radioDead.isChecked()) {
            answer3 = String.valueOf(radioDead.getText());
        } else if (radioGalilee.isChecked()) {
            answer3 = String.valueOf(radioGalilee.getText());
        }
        questionList.get(3).setUserAnswer(answer3);

        // get answer 4
        CheckBox checkboxWhite = findViewById(R.id.checkbox_white);
        CheckBox checkboxApple = findViewById(R.id.checkbox_apple);
        CheckBox checkboxNonstop = findViewById(R.id.checkbox_nonstop);
        boolean correct = false;
        if (checkboxWhite.isChecked() && !checkboxApple.isChecked() && checkboxNonstop.isChecked()) {
            correct = true;
        }
        String answer4 = "";
        if (correct) {
            answer4 = String.valueOf(checkboxWhite.getText()) + "|" + String.valueOf(checkboxNonstop.getText());
        } else {
            answer4 = "";
        }
        questionList.get(4).setUserAnswer(answer4);

        // get answer 5
        String answer5 = "";
        RadioButton radio2 = findViewById(R.id.radio_2);
        RadioButton radio5 = findViewById(R.id.radio_5);
        RadioButton radio10 = findViewById(R.id.radio_10);
        if (radio2.isChecked()) {
            answer5 = String.valueOf(radio2.getText());
        } else if (radio5.isChecked()) {
            answer5 = String.valueOf(radio5.getText());
        } else if (radio10.isChecked()) {
            answer5 = String.valueOf(radio10.getText());
        }
        questionList.get(5).setUserAnswer(answer5);
    }
}
