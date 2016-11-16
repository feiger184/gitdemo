package com.feicui.gaonews.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.feicui.gaonews.R;
import com.feicui.gaonews.adapter.NewsAdapter;
import com.feicui.gaonews.bean.NewsInfo;
import com.feicui.gaonews.biz.ParserNews;
import com.feicui.gaonews.utils.HttpCilentGetOrPost;
import com.feicui.gaonews.utils.NewsDBManager;

import java.util.ArrayList;

/**
 * 社会Fragment
 */

public class SocietyFragment extends Fragment {
    private ListView lv_news;
    private NewsAdapter newsAdapter;
    private String url;
    private String data;
    private ArrayList<NewsInfo> datalist = new ArrayList<NewsInfo>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MyHandlerMessage(msg);

        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_society_fragment, null);

        lv_news = (ListView) view.findViewById(R.id.lv_news);
        lv_news.setOnItemClickListener(onitemclicklistener);
        newsAdapter = new NewsAdapter(getActivity());
        lv_news.setAdapter(newsAdapter);

        LoadListData();//加载数据


        return view;
    }


    private void LoadListData() {
        url = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<NewsInfo> list = null;
                Message msg = handler.obtainMessage();
                NewsDBManager newsDBmager = NewsDBManager.getNewsDBManager(getActivity());
                if (newsDBmager.getNewsCount()) {
                    list = newsDBmager.queryNews();
                    msg.what = 1;
                    msg.obj = list;
                    handler.sendMessage(msg);

                } else {
                    try {
                        data = HttpCilentGetOrPost.HttpGet(url);
                        ParserNews parser = new ParserNews(getActivity());
                        list = parser.parser(data);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    msg.what = 2;
                    msg.obj = list;
                    handler.sendMessage(msg);
                }


            }
        }).start();
    }


    private void MyHandlerMessage(Message msg) {
        if (msg.what == 1) {
            datalist = (ArrayList<NewsInfo>) msg.obj;
            newsAdapter.setDataToAdapter(datalist);
            newsAdapter.notifyDataSetChanged();
        }

        if (msg.what == 2) {
            datalist = (ArrayList<NewsInfo>) msg.obj;
            newsAdapter.setDataToAdapter(datalist);
            newsAdapter.notifyDataSetChanged();
        }
    }

    AdapterView.OnItemClickListener onitemclicklistener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getActivity(), NewsActivity.class);
            NewsInfo newsinfo = datalist.get(i);
            String link = newsinfo.getLink();
            intent.putExtra("link", link);
            startActivity(intent);
        }
    };


}
