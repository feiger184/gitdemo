package com.feicui.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by 高 on 2016/11/4.
 */
public class myAdapter extends BaseAdapter {
    private int[] a;
    private Context context;

    public myAdapter(MainActivity mainActivity, int[] a) {
        this.a = a;
        this.context = mainActivity;
    }

    @Override
    public int getCount() {
        return a.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.layout_gridview_item, null);
            holder.im = (ImageView) view.findViewById(R.id.imagevie);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.im.setImageResource(a[i]);

        return view;
    }


    class ViewHolder {
        ImageView im;
    }
}
