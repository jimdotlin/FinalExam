package com.example.thefinalexam;

import android.Manifest;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;


public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    ArrayList<Actress> ACTRESS_ITEMS = new ArrayList<>();

    int clickNum = 0;
    String sortStr = "";
    private static String TAG = "MainActivity";
    private Adapter mAdapter;
    private RecyclerView mList;
    private static final int MY_PERMISSION_RECORD_AUDIO_REQUEST_CODE = 1;
    private MediaPlayer mplayer = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//----------------get database data------------------------------------------------------------
        getData(sortStr);

        mList = (RecyclerView) findViewById(R.id.recyclerViewTasks);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mList.setLayoutManager(layoutManager);
        mList.setHasFixedSize(true);


        mAdapter = new Adapter(this, ACTRESS_ITEMS);

        mList.setAdapter(mAdapter);

        //----------------------------------Click to Detail view----------------------------------------------


        mAdapter.setOnItemClickListener(new Adapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view , int position){

                Toast.makeText(MainActivity.this, ACTRESS_ITEMS.get(position).getName(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("Id",ACTRESS_ITEMS.get(position).getId());
                intent.putExtra("ActressName",ACTRESS_ITEMS.get(position).getName());
                intent.putExtra("ActressCup", ACTRESS_ITEMS.get(position).getCup());
                intent.putExtra("ActressHeight", ACTRESS_ITEMS.get(position).getHeight());
                intent.putExtra("ActressAge",ACTRESS_ITEMS.get(position).getAge());
                intent.putExtra("ImageUrl",ACTRESS_ITEMS.get(position).getPosterThumbnailUrl());
                startActivity(intent);
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();
                Uri ActressUri = Uri.parse("content://com.example.thefinalexam.ActressProvider/actress");
                getContentResolver().delete(ActressUri,"_id="+ACTRESS_ITEMS.get(position).getId(),null);
                ACTRESS_ITEMS.remove(position);
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyDataSetChanged();
            }
        }).attachToRecyclerView(mList);

        //---------------------------------Click to Question view----------------------------------------------
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ACTRESS_ITEMS.isEmpty()){
                    Intent QuestionIntent = new Intent(MainActivity.this, QuestionActivity.class);
                    startActivity(QuestionIntent);
                }else{
                    Toast.makeText(MainActivity.this, "請先新增女優!!", Toast.LENGTH_LONG).show();
                }

            }
        });
        setupPermissions();
        setupSharedPreferences();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.manu, menu);
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
            case R.id.action_sort :
                if(!ACTRESS_ITEMS.isEmpty()){
                    if(clickNum == 0){
                        clickNum++;
                        sortStr = "ActressCup";
                        Toast.makeText(MainActivity.this, "以罩杯排序", Toast.LENGTH_LONG).show();
                    }else if(clickNum == 1){
                        clickNum = 0;
                        sortStr = "ActressAge";
                        Toast.makeText(MainActivity.this, "以年齡排序", Toast.LENGTH_LONG).show();
                    }
                    ACTRESS_ITEMS.clear();
                    mAdapter.notifyDataSetChanged();
                    getData(sortStr);
                }else{
                    Toast.makeText(MainActivity.this, "請先新增女優!!", Toast.LENGTH_LONG).show();
                }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mplayer != null) {
            mplayer.stop();
            mplayer.release();
            mplayer = null;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

//        int i = MainActivity.class.hashCode();
//        Log.e(TAG,"hashcode : "+i);
        if (mplayer != null) {
                mplayer.start();
        }else if (mplayer == null){
            setupSharedPreferences();
            if (mplayer != null){
                mplayer.start();
            }
        }
    }
    private void setupPermissions() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                String[] permissionsWeNeed = new String[]{ Manifest.permission.RECORD_AUDIO };
                requestPermissions(permissionsWeNeed, MY_PERMISSION_RECORD_AUDIO_REQUEST_CODE);
            }
        } else {
            play();
        }
    }
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults){
        switch (requestCode){
            case MY_PERMISSION_RECORD_AUDIO_REQUEST_CODE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }else{
                    Toast.makeText(this, "Permission for audio not granted. Visualizer can't run.", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }
    }
    private void setupSharedPreferences(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String musicname = sharedPreferences.getString("music","music1");

        if (musicname.equals("music1")){
            mplayer = MediaPlayer.create(getApplicationContext(),R.raw.tokyo);
        } else if (musicname.equals("music2")) {
            mplayer = MediaPlayer.create(getApplicationContext(),R.raw.pokemon);
        }else if (musicname.equals("none")){
            Toast.makeText(this,"NO MUSIC",Toast.LENGTH_SHORT).show();
            mplayer = null;
        }
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key){
        String musicname = sharedPreferences.getString("music","music1");


//        Toast.makeText(this,String.valueOf(musicname.equals("music2")),Toast.LENGTH_SHORT).show();

        if (musicname.equals("music1")){
            mplayer = MediaPlayer.create(getApplicationContext(),R.raw.tokyo);
        }else if (musicname.equals("music2")){
            mplayer = MediaPlayer.create(getApplicationContext(),R.raw.pokemon);
        }else if (musicname.equals("none")){
            Toast.makeText(this,"NO MUSIC",Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    public void getData(String sortStr){
        Uri ActressUri = Uri.parse("content://com.example.thefinalexam.ActressProvider/actress");
//        ContentValues contentValues = new ContentValues();
//        getContentResolver().delete(ActressUri,null,null);//  ----清除表格----
//        contentValues.put("ActressName", "三上優雅");
//        contentValues.put("ActressCup","z");
//        contentValues.put("ActressAge","20");
//        contentValues.put("PosterUrl","https://i.imgur.com/gn1XeZ2.jpg");
//        getContentResolver().insert(ActressUri, contentValues);
        Cursor ActressCursor = getContentResolver().query(ActressUri, new String[]{"_id", "ActressName","ActressCup","ActressAge","ActressHeight","PosterUrl"}, null, null, sortStr);
        if (ActressCursor != null) {
            while (ActressCursor.moveToNext()) {
                Log.e(TAG, "ID:" + ActressCursor.getInt(ActressCursor.getColumnIndex("_id"))
                        + "  ActressName:" + ActressCursor.getString(ActressCursor.getColumnIndex("ActressName"))
                        +"  ActressCup:"+ActressCursor.getString(ActressCursor.getColumnIndex("ActressCup"))
                        +"  ActressAge:"+ActressCursor.getString(ActressCursor.getColumnIndex("ActressAge"))
                        +"  ActressHeight:"+ActressCursor.getString(ActressCursor.getColumnIndex("ActressHeight")));
                ACTRESS_ITEMS.add(new Actress(ActressCursor.getString(ActressCursor.getColumnIndex("_id")),ActressCursor.getString(ActressCursor.getColumnIndex("ActressName")),ActressCursor.getString(ActressCursor.getColumnIndex("ActressCup")),ActressCursor.getString(ActressCursor.getColumnIndex("ActressHeight")),ActressCursor.getString(ActressCursor.getColumnIndex("ActressAge")),ActressCursor.getString(ActressCursor.getColumnIndex("PosterUrl"))));
            }

            ActressCursor.close();
        }
    }
    public void play(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String musicname = sharedPreferences.getString("music","music1");
        if (mplayer != null){
        if (musicname.equals("music1")){
            mplayer = MediaPlayer.create(getApplicationContext(),R.raw.tokyo);
            mplayer.start();
        }else if (musicname.equals("music2")){
            mplayer = MediaPlayer.create(getApplicationContext(),R.raw.pokemon);
            mplayer.start();
        }else if (musicname.equals("none")){
            Toast.makeText(this,"NO MUSIC",Toast.LENGTH_SHORT).show();
        }
    }
    }

    public static void add_alarm(Context context, Calendar cal) {
        Log.e(TAG, "alarm add time: " + String.valueOf(cal.get(Calendar.MONTH)) + "." + String.valueOf(cal.get(Calendar.DATE)) + " " + String.valueOf(cal.get(Calendar.HOUR_OF_DAY)) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND));

        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.addCategory("ID." + String.valueOf(cal.get(Calendar.MONTH)) + "." + String.valueOf(cal.get(Calendar.DATE)) + "-" + String.valueOf((cal.get(Calendar.HOUR_OF_DAY) )) + "." + String.valueOf(cal.get(Calendar.MINUTE)) + "." + String.valueOf(cal.get(Calendar.SECOND)));
        String AlarmTimeTag = "Alarmtime " + String.valueOf(cal.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(cal.get(Calendar.MINUTE)) + ":" + String.valueOf(cal.get(Calendar.SECOND));

        intent.putExtra("title", "activity_app");
        intent.putExtra("time", AlarmTimeTag);

        PendingIntent pi = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi);

    }
}
