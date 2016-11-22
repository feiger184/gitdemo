package com.feicui.gaonews.activity;


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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.feicui.gaonews.R;
import com.feicui.gaonews.adapter.LeftMenuAdapter;
import com.feicui.gaonews.adapter.MFragmentPagerAdapter;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

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

    private int screenW;//显示屏宽度

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


    private void InitDrawerLayout() {

        DrawerLayout drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        LinearLayout rightmenu = (LinearLayout) findViewById(R.id.right_menu);
        DrawerLayout.LayoutParams rightparams = (DrawerLayout.LayoutParams) rightmenu.getLayoutParams();
        rightparams.width = getResources().getDisplayMetrics().widthPixels * 3 / 4;
        rightmenu.setLayoutParams(rightparams);

    }

    private void InitSlidingMenu() {

        ImageView menuleft = (ImageView) findViewById(R.id.menu_left_btn);

        
//        LeftMenuFragment left = new LeftMenuFragment();
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//
//        transaction.commit();

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
