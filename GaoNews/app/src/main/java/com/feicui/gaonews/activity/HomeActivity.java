package com.feicui.gaonews.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.feicui.gaonews.R;
import com.feicui.gaonews.adapter.LeftMenuAdapter;
import com.feicui.gaonews.adapter.MFragmentPagerAdapter;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;

/*
* 主界面
* */
public class HomeActivity extends FragmentActivity {
    private ImageView tab_line;
    private RadioGroup radioGroup;
    private RadioButton rb_society;
    private RadioButton rb_military;
    private RadioButton rb_other;
    private ArrayList<Fragment> fragmentArrayList;
    private FragmentManager fragmentManager;
    private ViewPager mViewPager;
    private DrawerLayout drawerlayout;
    private int screenW;//显示屏宽度
    private boolean isnotificationsave;// 通知图标的标识
    private ToggleButton tgl_beginoff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        InitSlidingMenu();//左侧侧滑菜单


        InitDrawerLayout();//右侧DrawerLayout


        InitRadioGroup(); //初始化RadioButton和RadioGroup

        InitTabLine();//TAB_line

        InitFragment(); //初始化Fragment，并添加到ArrayList中


        InitViewPager(); //初始化ViewPager

    }


    /*
    * 左侧侧滑菜单
    * */
    private void InitSlidingMenu() {

        ImageView menuleft = (ImageView) findViewById(R.id.menu_left_btn);

        final SlidingMenu slidingmenu = new SlidingMenu(this);
        slidingmenu.setMode(SlidingMenu.LEFT);//菜单模式:
        slidingmenu.setMenu(R.layout.layout_menu_left);//菜单布局
        slidingmenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);//菜单显示方式
//        slidingmenu.setTouchModeBehind(SlidingMenu.TOUCHMODE_MARGIN);//设置触摸滑动菜单时隐藏菜单

        slidingmenu.setBehindWidth(400);//菜单显示宽度  单位：px
        // 将菜单附加到当前的Activity窗口中：
        slidingmenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

//        //设置菜单与内容边缘的阴影效果
//        slidingmenu.setShadowWidth(10);
//        slidingmenu.setShadowDrawable(getResources().getDrawable(R.mipmap.ic_launcher));

        slidingmenu.setFadeEnabled(true);

        //菜单滑动时的渐变程度
        slidingmenu.setFadeDegree(1.0f);

        //设置滑动菜单的滑动尺度
        slidingmenu.setBehindScrollScale(0.25f);

        menuleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingmenu.toggle();
            }
        });


        ListView left_menulist = (ListView) findViewById(R.id.left_menu_list);
        ArrayList<String> list = new ArrayList<String>();
        list.add("新闻");
        list.add("收藏");
        list.add("本地");
        list.add("跟帖");
        list.add("图片");
        LeftMenuAdapter adapter = new LeftMenuAdapter(this, list);


        View head = LayoutInflater.from(this).inflate(R.layout.layout_leftmenu_item_head, null);
        View foot = LayoutInflater.from(this).inflate(R.layout.layout_leftmenu_item_foot, null);
        left_menulist.addHeaderView(head);
        left_menulist.addFooterView(foot);
        left_menulist.setAdapter(adapter);


        TextView tv_login = (TextView) findViewById(R.id.tv_login);

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LoginOnActivity.class);
                startActivity(intent);
            }
        });


    }


    /*
   * 右侧DrawerLayout
   * */
    private void InitDrawerLayout() {

        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        final LinearLayout rightmenu = (LinearLayout) findViewById(R.id.right_menu);
        DrawerLayout.LayoutParams rightparams = (DrawerLayout.LayoutParams) rightmenu.getLayoutParams();
        rightparams.width = getResources().getDisplayMetrics().widthPixels * 3 / 4;
        rightmenu.setLayoutParams(rightparams);
        ImageView menu_right_back = (ImageView) findViewById(R.id.im_right_back);

        menu_right_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.closeDrawer(rightmenu);//关闭右边菜单
            }
        });




        ImageView menu_right = (ImageView) findViewById(R.id.menu_right_btn);

        menu_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.openDrawer(rightmenu);//打开右边DrawerLayout菜单
            }
        });

        tgl_beginoff = (ToggleButton) findViewById(R.id.tgl_beginstart);
        getNotificationPreferences();
        tgl_beginoff.setChecked(isnotificationsave);
        tgl_beginoff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // ischecked 是你点击开关时 ，的状态 ，点开是true ，那么你就要创建Notification
                // 点击关的时候，，关闭所有Notification。
                if (isChecked) {

                    JPushInterface.resumePush(getApplicationContext());
                } else {

                    JPushInterface.stopPush(getApplicationContext());
                }

                // 点击了一下这个开关就会执行到这，保持状态 ，这个状态的值 是 true或者false
                saveNotificationPreferences(isChecked);
            }
        });


    }


    /**
     * 存状态
     */
    protected void saveNotificationPreferences(boolean arg1) {
        SharedPreferences sharepreference = getSharedPreferences("tongzhi",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharepreference.edit();

        editor.putBoolean("istongzhisave", arg1);
        editor.commit();

    }

    /**
     * 取状态
     */
    protected void getNotificationPreferences() {
        SharedPreferences preferences = getSharedPreferences("tongzhi",
                Context.MODE_PRIVATE);
        isnotificationsave = preferences.getBoolean("istongzhisave", true);

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


    /*
    * 动画
    * */
    private void InitTabLine() {
        tab_line = (ImageView) findViewById(R.id.tab_line);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        // 获取分辨率宽度
        screenW = dm.widthPixels;

        ViewGroup.LayoutParams mparams = tab_line.getLayoutParams();
        mparams.width = (screenW / 3);
        tab_line.setLayoutParams(mparams);


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


                LinearLayout.LayoutParams mparams = (LinearLayout.LayoutParams) tab_line.getLayoutParams();
                mparams.leftMargin = (int) ((position + positionOffset) * screenW / 3);
                tab_line.setLayoutParams(mparams);
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
