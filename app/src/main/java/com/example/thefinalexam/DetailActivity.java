package com.example.thefinalexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getIncomingIntent();
    }

    private void getIncomingIntent(){
//        if(getIntent().hasExtra("ActressName") && getIntent().hasExtra("ImageUrl")){
            String ID = getIntent().getStringExtra("Id");//這裡有ID可以用
            String ActressName = getIntent().getStringExtra("ActressName");
            String ActressCup = getIntent().getStringExtra("ActressCup");
            String ActressHeight = getIntent().getStringExtra("ActressHeight");
            String ActressAge = getIntent().getStringExtra("ActressAge");
            String ActressUrl = getIntent().getStringExtra("ImageUrl");
            setContent(ActressName,ActressUrl,ActressCup,ActressHeight,ActressAge);
//        }

    }

    private void setContent(String ActressName,String ActressUrl,String ActressCup,String ActressHeight,String ActressAge){
        TextView Name = findViewById(R.id.NameView);
        Name.setText("女優："+ActressName);
        TextView Cup = findViewById(R.id.CupView);
        Cup.setText("Cup："+ActressCup);
        TextView Age = findViewById(R.id.AgeView);
        Age.setText("年齡："+ActressAge);
        TextView Height = findViewById(R.id.HeightView);
        Height.setText("身高："+ActressHeight);
        ImageView image = findViewById(R.id.ActressimageView);
        Glide.with(this)
                .load(ActressUrl)
                .into(image);
    }
}
