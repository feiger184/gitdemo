package com.feicui.gaonews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.feicui.gaonews.R;
import com.feicui.gaonews.activity.HomeActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class LeftMenuAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> list = new ArrayList<String>();

    public LeftMenuAdapter(HomeActivity homeActivity, ArrayList list) {
        this.context = homeActivity;
        this.list = list;
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
        ViewHolder holder;
        if (view == null) {

            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.layout_leftmenu_item, null);
            holder.left_text = (TextView) view.findViewById(R.id.left_menu_text);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        holder.left_text.setText(list.get(i));

        return view;
    }

    class ViewHolder {

        TextView left_text;
    }
}
