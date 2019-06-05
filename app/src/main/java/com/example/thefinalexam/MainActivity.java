package com.example.thefinalexam;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Actress> ACTRESS_ITEMS = new ArrayList<>();


    private Adapter mAdapter;
    private RecyclerView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = (RecyclerView) findViewById(R.id.recyclerViewTasks);

        ACTRESS_ITEMS.add(new Actress("上原亞衣","E","155","55","20","https://i.imgur.com/O2AYCeh.jpg"));
        ACTRESS_ITEMS.add(new Actress("asd"," ","155","55","20","http://i.imgur.com/mVpDmzc.jpg"));
        ACTRESS_ITEMS.add(new Actress("zxc","A","155","55","20","http://i.imgur.com/mVpDmzc.jpg"));
        ACTRESS_ITEMS.add(new Actress("qwe","A","155","55","20","http://i.imgur.com/mVpDmzc.jpg"));
        ACTRESS_ITEMS.add(new Actress("fgh","A","155","55","20","http://i.imgur.com/mVpDmzc.jpg"));
        ACTRESS_ITEMS.add(new Actress("hjk","A","155","55","20","http://i.imgur.com/mVpDmzc.jpg"));
        ACTRESS_ITEMS.add(new Actress("bnm","A","155","55","20","http://i.imgur.com/mVpDmzc.jpg"));



        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mList.setLayoutManager(layoutManager);
        mList.setHasFixedSize(true);

        mAdapter = new Adapter(this, ACTRESS_ITEMS);

        mList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new Adapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view , int position){
                Toast.makeText(MainActivity.this, ACTRESS_ITEMS.get(position).getName(), 600).show();

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("ActressName",ACTRESS_ITEMS.get(position).getName());
                intent.putExtra("ActressCup", ACTRESS_ITEMS.get(position).getCup());
                intent.putExtra("ActressHeight", ACTRESS_ITEMS.get(position).getHeight());
                intent.putExtra("ActressWeight", ACTRESS_ITEMS.get(position).getWeight());
                intent.putExtra("ActressAge",ACTRESS_ITEMS.get(position).getAge());
                intent.putExtra("ImageUrl",ACTRESS_ITEMS.get(position).getPosterThumbnailUrl());
                startActivity(intent);
            }
        });

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
