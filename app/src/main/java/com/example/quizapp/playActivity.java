package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class playActivity extends AppCompatActivity {
    String[] question_list = {"What year was this car made? Ford Model T",
            "What year was this car made? Chevrolet Corvette",
            "What year was this car made? Volkswagen Beetle",
            "What year was this car made? Porsche 911",
            "What year was this car made? Toyota Corolla",
            "What year was this car made? Honda Civic",
            "What year was this car made? BMW 3 Series",
            "What year was this car made? Ford Mustang",
            "What year was this car made? Mercedes-Benz S-Class",
            "What year was this car made? Jeep Wrangler",
            "What year was this car made? Dodge Challenger"};
    String[] choose_list = {"1908", "1911", "2000", "1823",
            "2001", "1954", "1953", "1845",
            "1990", "1938", "1998", "1801",
            "1963", "1909", "1880", "2001",
            "1914", "2011", "1699", "1966",
            "1978", "1999", "1956", "1972",
            "1890", "1999", "1975", "1888",
            "1964", "1897", "2003", "1867",
            "1858", "1789", "1899", "1972",
            "1789", "1986", "1987", "1789",
            "1699", "1966", "1869", "1970"
    };
    String[] correct_list = {"1908", "1953", "1938", "1963", "1966", "1972", "1975", "1964", "1972", "1986", "1970"};


    TextView cpt_question, text_question;
    Button btn_choose1, btn_choose2, btn_choose3, btn_choose4, btn_next, btn_check;


    int currentQuestion = 0;
    int scorePlayer = 0;
    boolean isclickBtn = false;
    String valueChoose = "";
    Button btn_click;

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
        btn_next = findViewById(R.id.btn_next);


        findViewById(R.id.image_back).setOnClickListener(
                a -> finish()
        );
        remplirData();


        btn_check.setOnClickListener(
                view -> {
                    if (isclickBtn) {
                        isclickBtn = false;

                        if (!valueChoose.equals(correct_list[currentQuestion])) {

                            Toast.makeText(playActivity.this, "Incorrect. Correct answer is: " + correct_list[currentQuestion], Toast.LENGTH_LONG).show();
                            btn_click.setBackgroundResource(R.drawable.background_btn_erreur);
                        } else {

                            Toast.makeText(playActivity.this, "Correct", Toast.LENGTH_LONG).show();
                            btn_click.setBackgroundResource(R.drawable.background_btn_correct);
                            scorePlayer++;
                        }


                    } else {
                        Toast.makeText(playActivity.this, "Enter the answer", Toast.LENGTH_LONG).show();
                    }
                }
        );

        btn_next.setOnClickListener(view -> {
            new Handler().postDelayed(() -> {
                // Check if the user has selected an answer
                if (valueChoose.isEmpty()) {
                    Toast.makeText(playActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    return; // Don't proceed further until an answer is selected
                }

                if (currentQuestion != question_list.length - 1) {
                    // Proceed to the next question only if an answer is selected
                    currentQuestion = currentQuestion + 1;
                    remplirData();
                    valueChoose = "";
                    btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
                    btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
                    btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
                    btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);
                } else {
                    // This block is executed if it's the last question
                    Intent intent = new Intent(playActivity.this, ResulteActivity.class);
                    intent.putExtra("Result", scorePlayer);
                    startActivity(intent);
                    finish();
                }

            }, 200);
        });





    }

    void remplirData() {
        cpt_question.setText((currentQuestion + 1) + "/" + question_list.length);
        text_question.setText(question_list[currentQuestion]);

        btn_choose1.setText(choose_list[4 * currentQuestion]);
        btn_choose2.setText(choose_list[4 * currentQuestion + 1]);
        btn_choose3.setText(choose_list[4 * currentQuestion + 2]);
        btn_choose4.setText(choose_list[4 * currentQuestion + 3]);

    }

    public void ClickChoose(View view) {
        btn_click = (Button) view;

        if (isclickBtn) {
            btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);
        }
        chooseBtn();


    }

    void chooseBtn() {

        btn_click.setBackgroundResource(R.drawable.background_btn_choose_color);
        isclickBtn = true;
        valueChoose = btn_click.getText().toString();
    }
}