package com.amaliapps.tlvquiztoast;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create a Quiz and fill it with Question(s)
        createQuiz();
    }

    /**
     * Clear focus on touch outside for all EditText inputs.
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    /**
     * Create a Quiz object and populate it with Question objects
     */
    public void createQuiz() {
        Map<Integer, Question> questions = new HashMap<>();

        Question question1 = new Question(getString(R.string.question1_answer));
        questions.put(1, question1);

        Question question2 = new Question(getString(R.string.question2_answer));
        questions.put(2, question2);

        Question question3 = new Question(getString(R.string.question3_answer));
        questions.put(3, question3);

        Question question4 = new Question(getString(R.string.question4_answer1) + "|" + getString(R.string.question4_answer2));
        questions.put(4, question4);

        Question question5 = new Question(getString(R.string.question5_answer));
        questions.put(5, question5);

        quiz = new Quiz(questions);
    }

    /**
     * When the Submit button is clicked, store the answers in the Quiz object and
     * then calculate the score and display it in a toast
     *
     * @param view context
     */
    public void submitQuiz(View view) {
        storeAnswers();
        int score = quiz.calculateScore();

        // display toast with results
        String resultMessage = getString(R.string.score_message, score);
        int messageStringResource = (quiz.isSuccess() ? (R.string.pass_message) : (R.string.fail_message));
        resultMessage += "\n" + getString(messageStringResource);
        Toast.makeText(this, resultMessage, Toast.LENGTH_LONG).show();
    }

    /**
     * Get the answers from the user (the values from the controls) and store them in
     * the Quiz object we created. Later another function will check the quiz for correctness.
     */
    private void storeAnswers() {
        Map<Integer, Question> questionList = quiz.getQuestionList();

        // get answer 1
        String answer = getRadioButtonAnswer(R.id.radio_group_where);
        questionList.get(1).setUserAnswer(answer);

        // get answer 2
        EditText yearTextBox = findViewById(R.id.question_year_input);
        answer = yearTextBox.getText().toString();
        questionList.get(2).setUserAnswer(answer);

        // get answer 3
        answer = getRadioButtonAnswer(R.id.radio_group_sea);
        questionList.get(3).setUserAnswer(answer);

        // get answer 4
        CheckBox checkboxWhite = findViewById(R.id.checkbox_white);
        CheckBox checkboxApple = findViewById(R.id.checkbox_apple);
        CheckBox checkboxNonstop = findViewById(R.id.checkbox_nonstop);
        answer = "";
        if (checkboxWhite.isChecked()) {
            answer += checkboxWhite.getText() + "|";
        }
        if (checkboxApple.isChecked()) {
            answer += checkboxApple.getText() + "|";
        }
        if (checkboxNonstop.isChecked()) {
            answer += checkboxNonstop.getText() + "";
        }
        questionList.get(4).setUserAnswer(answer);

        // get answer 5
        answer = getRadioButtonAnswer(R.id.radio_group_beaches);
        questionList.get(5).setUserAnswer(answer);
    }

    /**
     * Get the text of the checked RadioButton in a RadioGroup
     *
     * @param id is the id of the RadioGroup (e.g. R.id.radio_group)
     * @return a String with the text of the checked RadioButton
     */
    private String getRadioButtonAnswer(int id) {
        RadioGroup radioGroup = findViewById(id);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        if (radioButtonID > -1) { // check if a RadioButton is checked at all
            RadioButton radioButton = radioGroup.findViewById(radioButtonID);
            return String.valueOf(radioButton.getText());
        } else return ""; // return an empty string if no RadioButton is checked
    }

    /**
     * Clear the quiz - restart the activity
     *
     * @param view context
     */
    public void resetQuiz(View view) {
        finish();
        startActivity(getIntent());
    }

    /**
     * @param menu options menu
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    /**
     * Handle menu options
     *
     * @param item menu item
     * @return true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.photo_credit_option:
                Toast.makeText(this, R.string.photo_credit_body, Toast.LENGTH_LONG).show();
                return true;
            case R.id.icons_credit_option:
                Toast.makeText(this, R.string.icons_credit_body, Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}