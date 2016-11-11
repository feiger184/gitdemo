package com.feicui.gaonews.biz;

import android.content.Context;

import com.feicui.gaonews.bean.NewsInfo;
import com.feicui.gaonews.utils.NewsDBManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * JSON 数据解析
 */

public class ParserNews {
    private Context context;

    public ParserNews() {
    }

    //构造函数
    public ParserNews(Context context) {

        this.context = context;
    }

    /*
    * 解析数据
    * */
    public ArrayList<NewsInfo> parser(String dataStr) {

        ArrayList<NewsInfo> newsList = new ArrayList<NewsInfo>();
        try {
            JSONObject jsonObject = new JSONObject(dataStr);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                int nid = object.getInt("nid");
                String title = object.getString("title");
                String icon = object.getString("icon");
                String summary = object.getString("summary");
                String link = object.getString("link");
                int type = object.getInt("type");
                String stamp = object.getString("stamp");
                NewsInfo newsInfo = new NewsInfo(nid, title, summary, icon, link, type, stamp);
                NewsDBManager.getNewsDBManager(context).insertNews(newsInfo);
                newsList.add(newsInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsList;
    }
}
