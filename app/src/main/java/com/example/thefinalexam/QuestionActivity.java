package com.example.thefinalexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {
    private TextView question;
    private TextView one;
    private TextView two;
    private TextView three;
    private TextView question_ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        question = (TextView)findViewById(R.id.question);
        one = (TextView)findViewById(R.id.one);
        two = (TextView)findViewById(R.id.two);
        three = (TextView)findViewById(R.id.three);
        question_ch = (TextView)findViewById(R.id.question_ch);


        question.setText("1234");
        one.setText("1234");
        two.setText("1234");
        three.setText("1234");
        question_ch.setText("1234");
    }
}
