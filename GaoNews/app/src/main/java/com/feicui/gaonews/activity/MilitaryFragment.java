package com.feicui.gaonews.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feicui.gaonews.R;

/**
 * Created by Administrator on 2016/11/17 0017.
 */

public class MilitaryFragment extends Fragment {


    public MilitaryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_military_fragment, null);
        return view;
    }
}
