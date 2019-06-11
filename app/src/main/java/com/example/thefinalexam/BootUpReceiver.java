package com.example.thefinalexam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class BootUpReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            // 收到廣播後要做的事

            //建立通知發布鬧鐘
            Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+8:00")); //取得時間
            for(int i = 0; i < 10; i++){
                cal.add(Calendar.MINUTE, 1);    //加一分鐘
                cal.set(Calendar.SECOND, 0);    //設定秒數為0
                MainActivity.add_alarm(context, cal);        //註冊鬧鐘
            }
        }
    }

}

