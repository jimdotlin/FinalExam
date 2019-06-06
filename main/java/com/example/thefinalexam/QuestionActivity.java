package com.example.thefinalexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Random;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        TextView show = findViewById(R.id.textView2);
        show.setText(RandomQuestion());
    }



    private void CheckYesAns() {
        Button b1 = (Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton rb1 = findViewById(R.id.yesButton);
                String ans = "";
                if (rb1.isChecked()) {
                    ans = "答對";
                } else {
                    ans = "答錯";
                }
                TextView show = findViewById(R.id.textView2);
                show.setText(ans);
            }
        });
    }

    private void CheckNoAns() {
        Button b1 = (Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton rb1 = findViewById(R.id.noButton);
                String ans = "";
                if (rb1.isChecked()) {
                    ans = "答對";
                } else {
                    ans = "答錯";
                }
                TextView show = findViewById(R.id.textView2);
                show.setText(ans);
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
                Questions = "請問三上悠亞的年齡是25歲嗎？";
                CheckYesAns();
                break;
            case 2:
                Questions = "請問波多野結衣的年齡是28歲嗎？";
                CheckNoAns();
                break;
            case 3:
                Questions = "請問大槻響的三圍是35-22-33嗎？";
                CheckYesAns();
                break;
            case 4:
                Questions = "請問紗倉真菜的三圍是35-25-33嗎？";
                CheckNoAns();
                break;
            case 5:
                Questions = "請問高崎聖子的罩杯是Ｇ嗎？";
                CheckYesAns();
                break;
            case 6:
                Questions = "請問橋本有菜的罩杯是Ｄ嗎？";
                CheckNoAns();
                break;
            case 7:
                Questions = "請問的上原亞衣年齡是26歲嗎？";
                CheckYesAns();
                break;
            case 8:
                Questions = "請問吉澤明步的年齡是28歲嗎？";
                CheckNoAns();
                break;
            case 9:
                Questions = "請問麻倉憂的三圍是33-22-33嗎？";
                CheckYesAns();
                break;
            case 10:
                Questions = "請問松下紗榮子的三圍是33-22-33嗎？";
                CheckNoAns();
                break;
            case 11:
                Questions = "請問明日花綺羅的罩杯是Ｇ嗎？";
                CheckYesAns();
                break;
            case 12:
                Questions = "請問濱崎真緒的罩杯是Ｄ嗎？";
                CheckNoAns();
                break;
            case 13:
                Questions = "請問早川瀨里奈的年齡是33歲嗎？";
                CheckYesAns();
                break;
            case 14:
                Questions = "請問椎名空的年齡是25歲嗎？";
                CheckNoAns();
                break;
            case 15:
                Questions = "請問篠田優的三圍是35-24-34嗎？";
                CheckYesAns();
                break;
            case 16:
                Questions = "請問水野朝陽的三圍是35-23-35嗎？";
                CheckNoAns();
                break;
            case 17:
                Questions = "請問美谷朱里的罩杯是Ｃ嗎？";
                CheckYesAns();
                break;
            case 18:
                Questions = "請問鈴村愛里的罩杯是Ｅ嗎？";
                CheckNoAns();
                break;
            case 19:
                Questions = "請問河北彩花的年齡是19歲嗎？";
                CheckYesAns();
                break;
            case 20:
                Questions = "請問葵司的罩杯是Ｄ嗎？";
                CheckNoAns();
                break;
        }
        return Questions;
    }
}
