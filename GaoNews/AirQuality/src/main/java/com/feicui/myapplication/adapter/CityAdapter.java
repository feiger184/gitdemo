package com.feicui.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.feicui.myapplication.R;
import com.feicui.myapplication.activity.FirstActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/14 0014.
 */

public class CityAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> listname = new ArrayList<String>();


    public CityAdapter(FirstActivity firstActivity  ) {

        this.context = firstActivity;
    }

    @Override
    public int getCount() {
        return listname.size();
    }

    @Override
    public Object getItem(int i) {
        return listname.get(i);
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
            view = LayoutInflater.from(context).inflate(R.layout.layout_citylist_item, null);
            holder.city_name = (TextView) view.findViewById(R.id.city_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.city_name.setText(listname.get(i));

        return view;
    }


    public void setNameDataToAdapter(ArrayList<String> listname) {
        this.listname = listname;
    }

    class ViewHolder {
        TextView city_name;
    }
}
