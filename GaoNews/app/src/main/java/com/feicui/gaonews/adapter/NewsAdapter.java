package com.feicui.gaonews.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicui.gaonews.R;
import com.feicui.gaonews.bean.ImageAsuncTaskListener;
import com.feicui.gaonews.bean.NewsInfo;
import com.feicui.gaonews.biz.ImageAsyncTask;
import com.feicui.gaonews.utils.ImageManager;

import java.util.ArrayList;

/**
 * 适配器
 */

public class NewsAdapter extends BaseAdapter implements ImageAsuncTaskListener {

    private Context context;

    private Bitmap defoultBitmap;
    private ArrayList<NewsInfo> list = new ArrayList<NewsInfo>();


    /*
    * 构造函数
    * */
    public NewsAdapter(Context context) {
        this.context = context;

        defoultBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.timeicon);

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.layout_news_listview_item, null);
            holder.tv_title = (TextView) view.findViewById(R.id.tv_title);
            holder.tv_summary = (TextView) view.findViewById(R.id.tv_summay);
            holder.im_icon = (ImageView) view.findViewById(R.id.im_icon);
            holder.tv_time = (TextView) view.findViewById(R.id.tv_listTime);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        NewsInfo news = list.get(i);
        String imageurl = news.getIcon();

        //从缓存中取图片
        Bitmap bitmap = ImageManager.getInstance().getImage(imageurl);
        if (bitmap != null) {
            //如果缓存中有图片，则取出并显示
            holder.im_icon.setImageBitmap(bitmap);
        } else {
            //缓存中没图片，加载图片
            new ImageAsyncTask(this).execute(imageurl);
            holder.im_icon.setImageBitmap(defoultBitmap);
        }
        holder.tv_title.setText(news.getTitle());
        holder.tv_summary.setText(news.getSummary());
        holder.tv_time.setText(news.getStamp());
        return view;
    }

    public void setDataToAdapter(ArrayList<NewsInfo> datalist) {
        this.list = datalist;
        notifyDataSetChanged();

    }

    @Override
    public void getImageBitmap(Bitmap bitmap) {
        this.defoultBitmap = bitmap;
    }


    class ViewHolder {
        TextView tv_title;
        TextView tv_summary;
        ImageView im_icon;
        TextView tv_time;
    }
}
