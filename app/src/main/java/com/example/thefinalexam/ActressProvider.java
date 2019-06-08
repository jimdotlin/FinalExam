package com.example.thefinalexam;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class ActressProvider extends ContentProvider {

    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    public static final String AUTHORITY = "com.example.thefinalexam.ActressProvider";
    public static final int ACTRESS_URI_CODE = 0;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


    static {
        uriMatcher.addURI(AUTHORITY, DBOpenHelper.ACTRESS_TABLE_NAME, ACTRESS_URI_CODE);
    }

    private String getTableName(Uri uri) {
        String tableName = null;
        switch (uriMatcher.match(uri)) {
            case ACTRESS_URI_CODE:
                tableName = DBOpenHelper.ACTRESS_TABLE_NAME;
                break;
        }
        return tableName;
    }

    public ActressProvider() {

    }

    @Override
    public boolean onCreate() {
        context = getContext();
        initProviderData();
        return false;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    private void initProviderData() {
        sqLiteDatabase = new DBOpenHelper(context).getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ActressName", "初始資料1");
        sqLiteDatabase.insert(DBOpenHelper.ACTRESS_TABLE_NAME, null, contentValues);
        contentValues.put("ActressName", "初始資料2");
        sqLiteDatabase.insert(DBOpenHelper.ACTRESS_TABLE_NAME, null, contentValues);
        contentValues.put("ActressName", "初始資料3");
        sqLiteDatabase.insert(DBOpenHelper.ACTRESS_TABLE_NAME, null, contentValues);
        contentValues.put("ActressName", "初始資料1");
        sqLiteDatabase.insert(DBOpenHelper.ACTRESS_TABLE_NAME, null, contentValues);
        contentValues.put("ActressName", "初始資料2");
        sqLiteDatabase.insert(DBOpenHelper.ACTRESS_TABLE_NAME, null, contentValues);
        contentValues.clear();

        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String tableName = getTableName(uri);
        if (tableName == null) {
            throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
        sqLiteDatabase.insert(tableName, null, values);
        context.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String tableName = getTableName(uri);
        if (tableName == null) {
            throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
        int count = sqLiteDatabase.delete(tableName, selection, selectionArgs);
        if (count > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String tableName = getTableName(uri);
        if (tableName == null) {
            throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
        return sqLiteDatabase.query(tableName, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String tableName = getTableName(uri);
        if (tableName == null) {
            throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
        int row = sqLiteDatabase.update(tableName, values, selection, selectionArgs);
        if (row > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return row;
    }


}
