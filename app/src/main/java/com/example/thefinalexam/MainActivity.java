package com.example.thefinalexam;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_LIST_ITEMS = 100;

    private Adapter mAdapter;
    private RecyclerView mNumbersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //data binding test
        String passed = getIntent().getStringExtra("pass");
        if(passed != null)
        {
            Toast.makeText(this,passed,Toast.LENGTH_LONG).show();
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNumbersList = (RecyclerView) findViewById(R.id.recyclerViewTasks);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNumbersList.setLayoutManager(layoutManager);
        mNumbersList.setHasFixedSize(true);

        mAdapter = new Adapter(NUM_LIST_ITEMS);

        mNumbersList.setAdapter(mAdapter);


        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent QuestionIntent = new Intent(MainActivity.this, QuestionActivity.class);
                startActivity(QuestionIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings :
                Intent SettingIntent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(SettingIntent);
                break;
            case R.id.action_add :
                Intent addIntent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(addIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
