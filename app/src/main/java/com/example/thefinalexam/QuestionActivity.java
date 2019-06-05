package com.example.thefinalexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Random;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Button b1 = (Button)findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView show = findViewById(R.id.textView2);
                show.setText(RandomQuestion());
            }
        });



    }


    private String RandomQuestion() {
        Random ran = new Random();
        int ranNum;
        String Questions = "";
        ranNum = ran.nextInt(20) + 1;
        switch (ranNum) {
            case 1:
                Questions = "1111";
                break;
            case 2:
                Questions = "2222";
                break;
            case 3:
                Questions = "3333";
                break;
            case 4:
                Questions = "4444";
                break;
            case 5:
                Questions = "5555";
                break;
            case 6:
                Questions = "6666";
                break;
            case 7:
                Questions = "7777";
                break;
            case 8:
                Questions = "8888";
                break;
            case 9:
                Questions = "9999";
                break;
            case 10:
                Questions = "1010";
                break;
            case 11:
                Questions = "1111";
                break;
            case 12:
                Questions = "1212";
                break;
            case 13:
                Questions = "1313";
                break;
            case 14:
                Questions = "1414";
                break;
            case 15:
                Questions = "1515";
                break;
            case 16:
                Questions = "1616";
                break;
            case 17:
                Questions = "1717";
                break;
            case 18:
                Questions = "1818";
                break;
            case 19:
                Questions = "1919";
                break;
            case 20:
                Questions = "2020";
                break;
        }

        return Questions;
    }
}
