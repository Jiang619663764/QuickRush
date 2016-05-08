package com.example.huhu.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.huhu.bean.ShoppingInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 */
public class DBManager {

    private DBHelper mHelper;
    private SQLiteDatabase mDataBase;

    public DBManager(Context context){

        mHelper=new DBHelper(context);
        mDataBase=mHelper.getWritableDatabase();
    }

    public void add(ShoppingInfo infos){
        mDataBase.beginTransaction();

        try{
                mDataBase.execSQL("INSERT INTO product VALUES(null, ?, ?, ?, ?)", new Object[]{infos.getProName(), infos.getProPrice(), infos.getProDetail(), infos.getProImage()});
            mDataBase.setTransactionSuccessful();
        }finally {
            mDataBase.endTransaction();
        }

    }
    public List<ShoppingInfo> query(){

        List<ShoppingInfo> info=new ArrayList<ShoppingInfo>();

        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            ShoppingInfo person = new ShoppingInfo();
            person.setProName(c.getString(c.getColumnIndex("name")));
            person.setProPrice(c.getFloat(c.getColumnIndex("price")));
            person.setProDetail(c.getString(c.getColumnIndex("info")));
            person.setProImage(c.getString(c.getColumnIndex("image")));
            info.add(person);
        }
        c.close();
        return info;
    }

    public Cursor queryTheCursor() {
        Cursor c = mDataBase.rawQuery("SELECT * FROM product", null);
        return c;
    }
}
