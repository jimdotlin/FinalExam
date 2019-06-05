package com.example.thefinalexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    private Button but;
    private EditText txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        but = (Button) findViewById(R.id.but);
        txt = (EditText) findViewById(R.id.txt);
    }
     public void  onClick(View view)
    {
        Intent home = new Intent( AddActivity.this , MainActivity.class);
        String pass = txt.getText() .toString();
        home.putExtra("pass",pass);
        startActivity(home);
    }

}
