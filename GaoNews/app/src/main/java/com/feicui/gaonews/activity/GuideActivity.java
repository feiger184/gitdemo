package com.feicui.gaonews.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicui.gaonews.R;
import com.feicui.gaonews.adapter.GuidViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
    private ViewPager mviewpager;

    private TextView jumpToHome;

    private ImageView[] icons = new ImageView[3];

    private List list;

    private boolean isFirstRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 去状态栏
        setContentView(R.layout.activity_guide);

        getSharedPreferences();

        if (!isFirstRun) {
            // 第一次肯定进不来 ，但是第二次，肯定进来，然后直接就跳转了
            Intent intetnt = new Intent(GuideActivity.this,
                    WelcomeActivity.class);
            startActivity(intetnt);

            finish();

        } else {
            initView();// 首次进入的时候要初始化控件。

            initLeadIcon();// 设置viewpager布局的下面三个小点

            setUpData();// 设置viewpager的数据源

            setUpAdapter();// 关联viewpager与adpter

            setClik();// 点击跳转的时候整个程序进入主界面
//        setPlayMusic();//开启背景音乐
        }

    }


    /*
    * 首次进入的时候要初始化控件。
    * */
    private void initView() {

        mviewpager = (ViewPager) findViewById(R.id.guide_viewpager);

        jumpToHome = (TextView) findViewById(R.id.guide_jumptohome);
    }


    /*
    * 设置viewpager布局的下面三个小点
    * */
    private void initLeadIcon() {

        icons[0] = (ImageView) findViewById(R.id.guide_icon1);
        icons[1] = (ImageView) findViewById(R.id.guide_icon2);
        icons[2] = (ImageView) findViewById(R.id.guide_icon3);
        icons[0].setImageResource(R.drawable.adware_style_selected);

    }

    /*
     * 设置viewpager的数据源
    **/
    private void setUpData() {

        list = new ArrayList();
        View view1 = LayoutInflater.from(this).inflate(R.layout.layout_guide_view_one, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.layout_guide_view_two, null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.layout_guide_view_three, null);
        list.add(view1);
        list.add(view2);
        list.add(view3);

    }


    /**
     * 关联viewpager与adpter
     */
    private void setUpAdapter() {

        GuidViewPagerAdapter adapter = new GuidViewPagerAdapter(list);
        mviewpager.setAdapter(adapter);

        mviewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {

                if (position >= 2) {
                    jumpToHome.setVisibility(View.VISIBLE);
                } else {
                    jumpToHome.setVisibility(View.GONE);
                }

                for (int i = 0; i < icons.length; i++) {
                    icons[i].setImageResource(R.drawable.adware_style_default);
                }

                icons[position].setImageResource(R.drawable.adware_style_selected);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    /*
  * 点击跳转的时候整个程序进入主界面
  * */
    private void setClik() {
        jumpToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this, HomeActivity.class);

                // 在点击的时候进行第一次状态的保存。
                savePreferences();

                startActivity(intent);
                finish();
            }
        });
    }


    /**
     * SharedPreferences的使用 ，，，，存值
     */
    protected void savePreferences() {

        // 1.因为别的地方也有可能用到SharedPreferences，所以必须有一个key。
        SharedPreferences preferences = getSharedPreferences("one",
                Context.MODE_PRIVATE);
        // 2.要想使用SharedPreferences 存值，必须获取到editor对象
        SharedPreferences.Editor editor = preferences.edit();
        // 3.利用editor存值 ，，，保存是否是第一次进入 ，
        editor.putBoolean("isFirstRun", false);
        // 4.最后一定要commit提交
        editor.commit();

    }

    /**
     * SharedPreferences的使用 ，，，，取值
     */
    private void getSharedPreferences() {

        SharedPreferences preferences = getSharedPreferences("one",
                Context.MODE_PRIVATE);
        isFirstRun = preferences.getBoolean("isFirstRun", true);

    }


}
