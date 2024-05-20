package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class playActivity extends AppCompatActivity {
    String[] question_list = {
            "What year was this car made? Ford Model T",
            "What year was this car made? Chevrolet Corvette",
            "What year was this car made? Volkswagen Beetle",
            "What year was this car made? Porsche 911",
            "What year was this car made? Ford Mustang",
            "What year was this car made? Nissan GT-R",
            "What year was this car made? BMW 3 Series",
            "What year was this car made? Chevrolet Camaro",
            "What year was this car made? Ferrari Dino",
            "What year was this car made? Lamborghini Countach",
            "What year was this car made? Dodge Challenger",
            "What year was this car made?Mercedes-Benz S-Class",
            "What year was this car made?Tesla Model S",
            "What year was this car made? Toyota Corolla",
            "What year was this car made? Honda Civic",
            "What year was this car made? BMW M3",
            "What year was this car made? Audi R8",
            "What year was this car made? Lamborghini Aventador",
            "What year was this car made? Ford GT",
            "What year was this car made? Bugatti Veyron"
    };

    String[] choose_list = {
            "1908", "1911", "2000", "1823",
            "2001", "1954", "1953", "1845",
            "1938", "1939", "1940", "1941",
            "1963", "1964", "1965", "1966",
            "1966", "1967", "1968", "1969",
            "1972", "1973", "1974", "1975",
            "1976", "1977", "1975", "1978",
            "1965", "1966", "1967", "1964",
            "1972", "1973", "1974", "1975",
            "1987", "1986", "1988", "1989",
            "1971", "1972", "1970", "1973",
            "1985", "1854", "2001", "1972",
            "2012", "2009", "1955", "1999",
            "1966", "1970", "1985", "2014",
            "1972", "1980", "1965", "1990",
            "1995", "1986", "1978", "2000",
            "1999", "2010", "2015", "2006",
            "2011", "2005", "2018", "2000",
            "2005", "1998", "2012", "2016",
            "2000", "2010", "2005", "2015"
    };

    String[] correct_list = {
            "1908", "1953", "1938", "1963", "1966",
            "1972", "1975", "1964", "1972", "1986",
            "1970", "1972", "2012", "1966", "1972",
            "1986", "2006", "2011", "2005", "2005"
    };

    TextView cpt_question, text_question, timerText;
    Button btn_choose1, btn_choose2, btn_choose3, btn_choose4, btn_check;

    int currentQuestion = 0;
    int scorePlayer = 0;
    boolean isclickBtn = false;
    boolean isAnswerChecked = false;
    String valueChoose = "";
    Button btn_click;
    boolean[] answeredQuestions;

    private static final long TIMER_DURATION = 4 * 60 * 1000; // 4 rope
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        cpt_question = findViewById(R.id.cpt_question);
        text_question = findViewById(R.id.text_question);
        btn_check = findViewById(R.id.btn_check);
        btn_choose1 = findViewById(R.id.btn_choose1);
        btn_choose2 = findViewById(R.id.btn_choose2);
        btn_choose3 = findViewById(R.id.btn_choose3);
        btn_choose4 = findViewById(R.id.btn_choose4);
        timerText = findViewById(R.id.timer);

        answeredQuestions = new boolean[question_list.length];

        findViewById(R.id.image_back).setOnClickListener(
                a -> finish()
        );

        startTimer();
        remplirData();

        btn_check.setOnClickListener(view -> {
            if (isclickBtn && !isAnswerChecked) {
                isclickBtn = false;
                isAnswerChecked = true;

                if (!valueChoose.equals(correct_list[currentQuestion])) {
                    Toast.makeText(playActivity.this, "Incorrect. Correct answer is: " + correct_list[currentQuestion], Toast.LENGTH_LONG).show();
                    btn_click.setBackgroundResource(R.drawable.background_btn_erreur1);
                    highlightCorrectAnswer();
                } else {
                    Toast.makeText(playActivity.this, "Correct", Toast.LENGTH_LONG).show();
                    btn_click.setBackgroundResource(R.drawable.background_btn_correct1);
                    scorePlayer++;
                }

                answeredQuestions[currentQuestion] = true;
                disableButtons();

                btn_check.setText("Next");
            } else if (isAnswerChecked) {
                if (currentQuestion != question_list.length - 1) {
                    currentQuestion++;
                    remplirData();
                    valueChoose = "";
                    isAnswerChecked = false;
                    btn_check.setText("Check");
                } else {
                    endQuiz();
                }
            } else {
                Toast.makeText(playActivity.this, "Please select an answer", Toast.LENGTH_LONG).show();
            }
        });
    }

    void remplirData() {
        cpt_question.setText((currentQuestion + 1) + "/" + question_list.length);
        text_question.setText(question_list[currentQuestion]);

        btn_choose1.setText(choose_list[4 * currentQuestion]);
        btn_choose2.setText(choose_list[4 * currentQuestion + 1]);
        btn_choose3.setText(choose_list[4 * currentQuestion + 2]);
        btn_choose4.setText(choose_list[4 * currentQuestion + 3]);

        resetButtons();
    }

    public void ClickChoose(View view) {
        btn_click = (Button) view;

        if (isclickBtn) {
            resetButtons();
        }
        chooseBtn();
    }

    void chooseBtn() {
        btn_click.setBackgroundResource(R.drawable.background_btn_choose_color1);
        isclickBtn = true;
        valueChoose = btn_click.getText().toString();
    }

    void resetButtons() {
        btn_choose1.setBackgroundResource(R.drawable.background_btn_default);
        btn_choose2.setBackgroundResource(R.drawable.background_btn_default);
        btn_choose3.setBackgroundResource(R.drawable.background_btn_default);
        btn_choose4.setBackgroundResource(R.drawable.background_btn_default);

        if (answeredQuestions[currentQuestion]) {
            btn_choose1.setEnabled(false);
            btn_choose2.setEnabled(false);
            btn_choose3.setEnabled(false);
            btn_choose4.setEnabled(false);
        } else {
            btn_choose1.setEnabled(true);
            btn_choose2.setEnabled(true);
            btn_choose3.setEnabled(true);
            btn_choose4.setEnabled(true);
        }
    }

    void disableButtons() {
        btn_choose1.setEnabled(false);
        btn_choose2.setEnabled(false);
        btn_choose3.setEnabled(false);
        btn_choose4.setEnabled(false);
    }

    void highlightCorrectAnswer() {
        if (btn_choose1.getText().toString().equals(correct_list[currentQuestion])) {
            btn_choose1.setBackgroundResource(R.drawable.background_btn_correct1);
        } else if (btn_choose2.getText().toString().equals(correct_list[currentQuestion])) {
            btn_choose2.setBackgroundResource(R.drawable.background_btn_correct1);
        } else if (btn_choose3.getText().toString().equals(correct_list[currentQuestion])) {
            btn_choose3.setBackgroundResource(R.drawable.background_btn_correct1);
        } else if (btn_choose4.getText().toString().equals(correct_list[currentQuestion])) {
            btn_choose4.setBackgroundResource(R.drawable.background_btn_correct1);
        }
    }

    void startTimer() {
        countDownTimer = new CountDownTimer(TIMER_DURATION, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                timerText.setText(String.format("%02d:%02d", minutes, seconds));
            }

            @Override
            public void onFinish() {
                endQuiz();
            }
        };
        countDownTimer.start();
    }

    void endQuiz() {
        Intent intent = new Intent(playActivity.this, ResulteActivity.class);
        intent.putExtra("Result", scorePlayer);
        startActivity(intent);
        finish();
    }
}
