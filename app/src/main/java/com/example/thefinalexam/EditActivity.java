package com.example.thefinalexam;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    private Button finishButton;
    private EditText editName;
    private EditText editCup;
    private EditText editHeight;
    private EditText editAge;
    private EditText editUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        final String Id = getIntent().getStringExtra("Id");
        String ActressName = getIntent().getStringExtra("ActressName");
        String ActressCup = getIntent().getStringExtra("ActressCup");
        String ActressHeight = getIntent().getStringExtra("ActressHeight");
        String ActressAge = getIntent().getStringExtra("ActressAge");
        String ActressUrl = getIntent().getStringExtra("ActressUrl");
        setContent(ActressName,ActressUrl,ActressCup,ActressHeight,ActressAge);
        finishButton = (Button)findViewById(R.id.finishButton);
        editName = (EditText) findViewById(R.id.editName);
        editCup = (EditText) findViewById(R.id.editCup);
        editHeight = (EditText) findViewById(R.id.editHeight);
        editAge = (EditText) findViewById(R.id.editAge);
        editUrl = (EditText) findViewById(R.id.editUrl);
        finishButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view){
                Uri ActressUri = Uri.parse("content://com.example.thefinalexam.ActressProvider/actress");
                ContentValues contentValues = new ContentValues();
                contentValues.put("ActressName",editName.getText().toString());
                contentValues.put("ActressCup",editCup.getText().toString());
                contentValues.put("ActressAge",editAge.getText().toString());
                contentValues.put("ActressHeight",editHeight.getText().toString());
                contentValues.put("PosterUrl",editUrl.getText().toString());
                getContentResolver().update(ActressUri,contentValues,"_id="+Id,null);
                Intent mainIntent = new Intent( EditActivity.this , MainActivity.class);
                startActivity(mainIntent);
            }
        });

    }

    private void setContent(String ActressName,String ActressUrl,String ActressCup,String ActressHeight,String ActressAge){
        EditText Name = findViewById(R.id.editName);
        Name.setText(ActressName);
        EditText Cup = findViewById(R.id.editCup);
        Cup.setText(ActressCup);
        EditText Age = findViewById(R.id.editAge);
        Age.setText(ActressAge);
        EditText Height = findViewById(R.id.editHeight);
        Height.setText(ActressHeight);
        EditText image = findViewById(R.id.editUrl);
        image.setText(ActressUrl);
    }
}
