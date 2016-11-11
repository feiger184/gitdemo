package com.feicui.gaonews.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 创建数据库
 */

public class NewsDBHelper extends SQLiteOpenHelper {

    /*
    * 创建数据库newsss
    * */
    public NewsDBHelper(Context context) {
        super(context, "newsss.db", null, 1);
    }

    /*
    * 创建newsss表
    * */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE newsss(_id INTEGER PRIMARY KEY AUTOINCREMENT , nid integer,title text, summary text, icon text,  link text, type integer, stamp text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
