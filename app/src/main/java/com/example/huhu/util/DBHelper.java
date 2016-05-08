package com.example.huhu.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/5/4.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "quick.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS product" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, price FLOAT, info TEXT,image TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
