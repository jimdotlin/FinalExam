package com.example.thefinalexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity{
    private TextView question;
    private TextView question_ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        question = (TextView)findViewById(R.id.question);
        question_ch = (TextView)findViewById(R.id.question_ch);
    }
    
    public void  onClick(View view)
    {
        Intent home = new Intent( AnswerActivity.this , MainActivity.class);
//        String pass = txt.getText() .toString();
//        home.putExtra("pass",pass);
        startActivity(home);
    }
}
