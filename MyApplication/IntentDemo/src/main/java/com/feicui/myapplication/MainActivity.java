package com.feicui.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void clickButton(View view) {
        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);

        switch (view.getId()) {
            case R.id.button_main_call://直接拨号
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:10086"));
                break;
            case R.id.button_main_dial://启动拨号界面
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                break;
            case R.id.button_main_dialer://显示拨号界面
                intent.setAction("com.android.phone.action.TOUCH_DIALER");
                break;
            case R.id.button_main_sms://发信息
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:10086"));
                intent.putExtra("sms_body", "该吃饭了，下课吧！");
                break;
            case R.id.button_main_setting://跳到设置界面
                intent.setAction("android.settings.SETTINGS");
                break;
            case R.id.button_main_datesetting://跳到日期设置界面
                intent.setAction("android.settings.DATE_SETTINGS");
                break;
            case R.id.button_main_soundsetting://跳到声音设置界面
                intent.setAction("android.settings.SOUND_SETTINGS");
                break;
            case R.id.button_main_wifisetting://跳到WIFI设置界面
                intent.setAction("android.settings.WIFI_SETTINGS");
                break;
            case R.id.button_main_contacts: //跳到联系人界面
                intent.setAction("com.android.contacts.action.LIST_CONTACTS");
                break;
            case R.id.button_main_web://跳到浏览器界面
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                break;
            case R.id.button_main_showimage://跳到图片浏览界面
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(
                        Uri.fromFile(new File("mnt/sdcard/Download/landscape.jpg")),
                        "image/*");
                break;
            case R.id.button_main_showtext://跳到文本浏览界面
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(
                        Uri.fromFile(new File("mnt/sdcard/Download/info.txt")),
                        "text/*");
                break;
            case R.id.button_main_playaudio://跳到播放音乐界面
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(
                        "mnt/sdcard/Download/heavencity.mp3")), "audio/*");
                break;
            case R.id.button_main_playvideo://跳到播放视频界面
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(
                        Uri.fromFile(new File("mnt/sdcard/Download/girl.3gp")),
                        "video/*");
                break;
            case R.id.button_main_home://返回主页
                intent.setAction("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.HOME");
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
