package com.example.thefinalexam;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    private Button addButton;
    private EditText addName;
    private EditText addCup;
    private EditText addHeight;
    private EditText addAge;
    private EditText addUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        addButton = (Button)findViewById(R.id.addButton);
        addName = (EditText) findViewById(R.id.editName);
        addCup = (EditText) findViewById(R.id.editCup);
        addHeight = (EditText) findViewById(R.id.editHeight);
        addAge = (EditText) findViewById(R.id.editAge);
        addUrl = (EditText) findViewById(R.id.editUrl);
    }

    public void OnClick(View view){

        Uri ActressUri = Uri.parse("content://com.example.thefinalexam.ActressProvider/actress");
        ContentValues contentValues = new ContentValues();
//        getContentResolver().delete(ActressUri,null,null);//  -------清除表格-------
        contentValues.put("ActressName",addName.getText().toString());
        contentValues.put("ActressCup",addCup.getText().toString());
        contentValues.put("ActressHeight",addHeight.getText().toString());
        contentValues.put("ActressAge",addAge.getText().toString());
        contentValues.put("PosterUrl",addUrl.getText().toString());
        getContentResolver().insert(ActressUri, contentValues);
        Intent mainIntent = new Intent( AddActivity.this , MainActivity.class);
        startActivity(mainIntent);
    }
}
