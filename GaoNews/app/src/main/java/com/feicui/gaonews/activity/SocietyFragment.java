package com.feicui.gaonews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.feicui.gaonews.R;
import com.feicui.gaonews.adapter.NewsAdapter;
import com.feicui.gaonews.bean.NewsInfo;
import com.feicui.gaonews.biz.ParserNews;
import com.feicui.gaonews.utils.HttpCilentGetOrPost;
import com.feicui.gaonews.utils.NewsDBManager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

/**
 * 社会Fragment
 */

public class SocietyFragment extends Fragment {
    private PullToRefreshListView lv_news;
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

        lv_news = (PullToRefreshListView) view.findViewById(R.id.lv_news);
        lv_news.setMode(PullToRefreshBase.Mode.BOTH);

        lv_news.setOnRefreshListener(onRefreshListener);
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
            lv_news.onRefreshComplete();
        }

        if (msg.what == 2) {
            datalist = (ArrayList<NewsInfo>) msg.obj;
            newsAdapter.setDataToAdapter(datalist);
            newsAdapter.notifyDataSetChanged();
            lv_news.onRefreshComplete();
        }
    }

    AdapterView.OnItemClickListener onitemclicklistener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getActivity(), NewsActivity.class);

            NewsInfo newsinfo = datalist.get(i-1);
            String link = newsinfo.getLink();
            intent.putExtra("link", link);
            startActivity(intent);
        }
    };


    PullToRefreshBase.OnRefreshListener2 onRefreshListener = new PullToRefreshBase.OnRefreshListener2() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {


            String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                    DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

            refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

            LoadListData();


        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
            LoadListData();

        }
    };


}
