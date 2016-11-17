package com.feicui.gaonews.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.feicui.gaonews.R;
import com.feicui.gaonews.adapter.MFragmentPagerAdapter;

import java.util.ArrayList;

/*
* 主界面
* */
public class HomeActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton rb_society;
    private RadioButton rb_military;
    private RadioButton rb_other;
    private ArrayList<Fragment> fragmentArrayList;
    private FragmentManager fragmentManager;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //初始化RadioButton和RadioGroup
        InitRadioGroup();

        //初始化Fragment，并添加到ArrayList中
        InitFragment();

        //初始化ViewPager
        InitViewPager();

//        SocietyFragment societyFragment = new SocietyFragment();
//
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.lv_news_fragment, societyFragment);
//        fragmentTransaction.commit();
//
//
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//
//                //获取FragmentManager
//                FragmentManager fragmentManager = getFragmentManager();
//                //开启事务
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                switch (i) {
//                    case R.id.rb_society:
//
//                        SocietyFragment societyFragment = new SocietyFragment();
//
//                        //替换布局
//                        fragmentTransaction.replace(R.id.lv_news_fragment, societyFragment);
//                        //提交事务
//                        fragmentTransaction.commit();
//
//
//                        break;
//                    case R.id.rb_other:
//                        OthersFragment othersFragment = null;
//                        if (othersFragment == null) {
//                            othersFragment = new OthersFragment();
//                        }
//                        fragmentTransaction.replace(R.id.lv_news_fragment, othersFragment);
//
//                        fragmentTransaction.commit();
//
//                        break;
//                }
//            }
//        });
    }


    /**
     * 初始化RadioButton和RadioGroup
     */
    private void InitRadioGroup() {
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        rb_society = (RadioButton) findViewById(R.id.rb_society);
        rb_military = (RadioButton) findViewById(R.id.rb_military);
        rb_other = (RadioButton) findViewById(R.id.rb_other);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_society:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_military:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_other:
                        mViewPager.setCurrentItem(2);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 初始化Fragment，并添加到ArrayList中
     */
    private void InitFragment() {
        fragmentArrayList = new ArrayList<Fragment>();
        fragmentArrayList.add(new SocietyFragment());
        fragmentArrayList.add(new MilitaryFragment());
        fragmentArrayList.add(new OthersFragment());
        fragmentManager = getSupportFragmentManager();
    }


    /**
     * 初始化ViewPager
     */
    private void InitViewPager() {

        mViewPager = (ViewPager) findViewById(R.id.lv_news_fragment);

        mViewPager.setAdapter(new MFragmentPagerAdapter(fragmentManager, fragmentArrayList));

//        //让ViewPager缓存2个页面
//        mViewPager.setOffscreenPageLimit(1);

        //设置默认打开第一页
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rb_society.setChecked(true);
                        break;
                    case 1:
                        rb_military.setChecked(true);
                        break;
                    case 2:
                        rb_other.setChecked(true);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
