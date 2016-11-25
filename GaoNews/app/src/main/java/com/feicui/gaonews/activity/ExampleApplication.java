package com.feicui.gaonews.activity;

import android.app.Application;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;

/**
 * For developer startup JPush SDK
 * 
 * 一般建议在自定义 Application 类里初始化。也可以在主 Activity 里。
 */
public class ExampleApplication extends Application {


    @Override
    public void onCreate() {    	     

         super.onCreate();

        Log.i("JMessageDemoApplication", "Application onCreate");

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(getApplicationContext());
    }

}
