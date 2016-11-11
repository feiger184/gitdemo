package com.feicui.gaonews.activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.feicui.gaonews.R;
import com.feicui.gaonews.adapter.NewsAdapter;
import com.feicui.gaonews.bean.ImageAsuncTaskListener;
import com.feicui.gaonews.bean.NewsInfo;
import com.feicui.gaonews.biz.ImageAsyncTask;
import com.feicui.gaonews.biz.ParserNews;
import com.feicui.gaonews.utils.HttpCilentGetOrPost;
import com.feicui.gaonews.utils.NewsDBManager;

import java.util.ArrayList;

/*
* 主界面
* */
public class HomeActivity extends AppCompatActivity {
    private String url;
    private String data;
    private ListView lv_news;
    private NewsAdapter newsadapter;//适配器
    private ArrayList<NewsInfo> datalist;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            myHandleMessage(msg);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();//初始化控件

        loadData();//加载数据


    }

    /*
    * 加载数据
    * */
    private void loadData() {
        url = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<NewsInfo> list = null;
                Message msg = mhandler.obtainMessage();
                NewsDBManager newsDBManager = NewsDBManager.getNewsDBManager(HomeActivity.this);
                if (newsDBManager.getNewsCount()) {

                    list = newsDBManager.queryNews();
                    msg.what = 2;
                    msg.obj = list;
                    mhandler.sendMessage(msg);

                } else {

                    try {
                        data = HttpCilentGetOrPost.HttpGet(url);
                        ParserNews parserNews = new ParserNews(HomeActivity.this);
                        list = parserNews.parser(data);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    msg.what = 1;
                    msg.obj = list;
                    mhandler.sendMessage(msg);

                }

            }
        }).start();
    }

    /*
    * 初始化控件
    * */
    private void initView() {

        lv_news = (ListView) findViewById(R.id.lv_news);
        lv_news.setOnItemClickListener(onitemclicklistener);
        newsadapter = new NewsAdapter(HomeActivity.this);
        lv_news.setAdapter(newsadapter);


    }

    protected void myHandleMessage(Message msg) {
        if (msg.what == 1) {
            datalist = (ArrayList<NewsInfo>) msg.obj;

            newsadapter.setDataToAdapter(datalist);
            newsadapter.notifyDataSetChanged();
        }
        if (msg.what == 2) {
            datalist = (ArrayList<NewsInfo>) msg.obj;
            newsadapter.setDataToAdapter(datalist);
            newsadapter.notifyDataSetChanged();
        }


    }

    //列表监听
    AdapterView.OnItemClickListener onitemclicklistener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(HomeActivity.this, NewsActivity.class);
            NewsInfo news = datalist.get(i);
            String link = news.getLink();
            intent.putExtra("link", link);
            startActivity(intent);
        }
    };


   
}
