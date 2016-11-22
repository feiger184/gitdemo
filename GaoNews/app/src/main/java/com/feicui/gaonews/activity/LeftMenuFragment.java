package com.feicui.gaonews.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feicui.gaonews.R;

/**
 * 左侧 侧滑菜单  Fragment
 */

public class LeftMenuFragment extends Fragment {


    public LeftMenuFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_menu_left, null);

        return view;
    }





}
