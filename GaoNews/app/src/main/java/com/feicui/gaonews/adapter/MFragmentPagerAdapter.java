package com.feicui.gaonews.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class MFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentArrayList;

    public MFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> fragmentArrayList) {

        super(fragmentManager);
        this.fragmentArrayList = fragmentArrayList;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
