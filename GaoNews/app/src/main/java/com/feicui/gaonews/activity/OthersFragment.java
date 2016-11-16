package com.feicui.gaonews.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feicui.gaonews.R;

/**
 * Created by Administrator on 2016/11/16 0016.
 */

public class OthersFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_others_fragment, null);

        return view;
    }
}
