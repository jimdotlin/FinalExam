package com.example.thefinalexam;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Locale;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DATA_BASE_NAME = "actress.db";
    private static final int DATE_BASE_VERSION = 1;
//    private static SQLiteDatabase database;
    public static final String ACTRESS_TABLE_NAME = "actress";

    private final String CREATE_ACTRESS_TABLE = "create table " + ACTRESS_TABLE_NAME
            + "(_id integer primary key autoincrement, ActressName text, ActressCup text, ActressAge text, ActressHeight text,PosterUrl text)";


    public DBOpenHelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATE_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL(CREATE_ACTRESS_TABLE);
         db.setLocale(Locale.CHINESE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
