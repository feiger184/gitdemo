package com.feicui.gaonews.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.feicui.gaonews.bean.NewsInfo;

import java.util.ArrayList;

/**
 * 数据库News.db管理
 */

public class NewsDBManager {
    private NewsDBHelper newsDBHelper;
    private static NewsDBManager newsDBManager;

    private NewsDBManager(Context context) {
        newsDBHelper = new NewsDBHelper(context);
    }

    /*
    * 单例模式
    * */
    public static NewsDBManager getNewsDBManager(Context context) {
        if (newsDBManager == null) {
            newsDBManager = new NewsDBManager(context);
        }
        return newsDBManager;
    }

    /*
    * 添加数据
    * */
    public void insertNews(NewsInfo newsInfo) {
        SQLiteDatabase db = newsDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nid", newsInfo.getNid());
        contentValues.put("title", newsInfo.getTitle());
        contentValues.put("summary", newsInfo.getSummary());
        contentValues.put("icon", newsInfo.getIcon());
        contentValues.put("link", newsInfo.getLink());
        contentValues.put("type", newsInfo.getType());
        contentValues.put("stamp", newsInfo.getStamp());
        db.insert("newsss", null, contentValues);
        db.close();
    }

    /*
    * 数据库是否有数据（返回值true有 false无）
    * */
    public boolean getNewsCount() {
        SQLiteDatabase db = newsDBHelper.getReadableDatabase();
        String sql = "select * from newsss";
        Cursor cursor = db.rawQuery(sql, null);
        long len = cursor.getCount();
        cursor.close();
        db.close();

        if (len > 0) {
            return true;
        }
        return false;
    }

    /*
    * 查询数据库
    * */
    public ArrayList<NewsInfo> queryNews() {
        ArrayList<NewsInfo> list = new ArrayList<NewsInfo>();
        SQLiteDatabase db = newsDBHelper.getReadableDatabase();
        String sql = "select * from newsss";//查询语句
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int nid = cursor.getInt(cursor.getColumnIndex("nid"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String summary = cursor.getString(cursor.getColumnIndex("summary"));

                String icon = cursor.getString(cursor.getColumnIndex("icon"));

                String link = cursor.getString(cursor.getColumnIndex("link"));

                String stamp = cursor.getString(cursor.getColumnIndex("stamp"));
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                NewsInfo newsInfo = new NewsInfo(nid, title, summary, icon, link, type, stamp);
                list.add(newsInfo);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return list;
    }
}
