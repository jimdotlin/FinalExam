package com.example.thefinalexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        final String ActressName = getIntent().getStringExtra("ActressName");
        final String ActressCup = getIntent().getStringExtra("ActressCup");
        final String ActressHeight = getIntent().getStringExtra("ActressHeight");
        final String ActressAge = getIntent().getStringExtra("ActressAge");
        final String ActressUrl = getIntent().getStringExtra("ImageUrl");
        setContent(ActressName,ActressUrl,ActressCup,ActressHeight,ActressAge);
        final String ID = getIntent().getStringExtra("Id");//這裡有ID可以用
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent EditIntent = new Intent(DetailActivity.this, EditActivity.class);
                EditIntent.putExtra("Id",ID);
                EditIntent.putExtra("ActressName",ActressName);
                EditIntent.putExtra("ActressCup",ActressCup);
                EditIntent.putExtra("ActressHeight",ActressHeight);
                EditIntent.putExtra("ActressAge",ActressAge);
                EditIntent.putExtra("ActressUrl",ActressUrl);
                startActivity(EditIntent);
            }
        });
    }


    private void setContent(String ActressName,String ActressUrl,String ActressCup,String ActressHeight,String ActressAge){
        TextView Name = findViewById(R.id.NameView);
        Name.setText("女優："+ActressName);
        TextView Cup = findViewById(R.id.CupView);
        Cup.setText("三圍："+ActressCup);
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
