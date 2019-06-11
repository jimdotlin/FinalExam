package com.example.thefinalexam;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "MainActivity";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bData = intent.getExtras();
        if(bData.get("title").equals("activity_app"))
        {
            NotificationUtils.remindUserBecauseCharging(context);
        }
    }



}
