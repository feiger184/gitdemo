package com.feicui.gaonews.activity;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.feicui.gaonews.R;

/*
* 主界面
* */
public class HomeActivity extends AppCompatActivity {
    private RadioButton rb_society;
    private RadioButton rb_other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        rb_society = (RadioButton) findViewById(R.id.rb_society);
        rb_other = (RadioButton) findViewById(R.id.rb_other);

        SocietyFragment societyFragment = new SocietyFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.lv_news_fragment, societyFragment);
        fragmentTransaction.commit();


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                //获取FragmentManager
                FragmentManager fragmentManager = getFragmentManager();
                //开启事务
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (i) {
                    case R.id.rb_society:

                        SocietyFragment societyFragment = new SocietyFragment();

                        //替换布局
                        fragmentTransaction.replace(R.id.lv_news_fragment, societyFragment);
                        //提交事务
                        fragmentTransaction.commit();


                        break;
                    case R.id.rb_other:
                        OthersFragment othersFragment = null;
                        if (othersFragment == null) {
                            othersFragment = new OthersFragment();
                        }
                        fragmentTransaction.replace(R.id.lv_news_fragment, othersFragment);

                        fragmentTransaction.commit();

                        break;
                }
            }
        });
    }


}
