package com.feicui.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CheckBox checkBox_main_hobby1;
    private CheckBox checkBox_main_hobby2;
    private CheckBox checkBox_main_hobby3;
    private CheckBox checkBox_main_hobby4;
    private CheckBox checkBox_main_selectall;
    private Button button_main_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        checkBox_main_hobby1 = (CheckBox) findViewById(R.id.checkBox_main_hobby1);
        checkBox_main_hobby2 = (CheckBox) findViewById(R.id.checkBox_main_hobby2);
        checkBox_main_hobby3 = (CheckBox) findViewById(R.id.checkBox_main_hobby3);
        checkBox_main_hobby4 = (CheckBox) findViewById(R.id.checkBox_main_hobby4);
        checkBox_main_selectall = (CheckBox) findViewById(R.id.checkBox_main_selectall);
        button_main_submit = (Button) findViewById(R.id.button_main_submit);
        button_main_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "您选择了：" + getResult(),
                        Toast.LENGTH_SHORT).show();

            }
        });

// 定义一个有名字的监听器类。之所以不用匿名内部类形式，是因为有多个控件都要使用这同一个监听器


        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!buttonView.isChecked()) {
                    checkBox_main_selectall.setChecked(false);
                }
                if (checkBox_main_hobby1.isChecked()
                        && checkBox_main_hobby2.isChecked()
                        && checkBox_main_hobby3.isChecked()
                        && checkBox_main_hobby4.isChecked()) {
                    checkBox_main_selectall.setChecked(true);
                }
                Toast.makeText(MainActivity.this, "您选择了：" + getResult(),
                        Toast.LENGTH_SHORT).show();
            }
        };

        checkBox_main_hobby1.setOnCheckedChangeListener(listener);
        checkBox_main_hobby2.setOnCheckedChangeListener(listener);
        checkBox_main_hobby3.setOnCheckedChangeListener(listener);
        checkBox_main_hobby4.setOnCheckedChangeListener(listener);

// 给全选checkbox设置单击监听事件

        checkBox_main_selectall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = checkBox_main_selectall.isChecked();
                checkBox_main_hobby1.setChecked(flag);
                checkBox_main_hobby2.setChecked(flag);
                checkBox_main_hobby3.setChecked(flag);
                checkBox_main_hobby4.setChecked(flag);
            }
        });

    }


    // 获取多选项中被勾选的结果。利用isChecked()方法来判断哪个选项被勾选
    private String getResult() {
        StringBuilder sb = new StringBuilder();
        if (checkBox_main_hobby1.isChecked()) {
            sb.append(checkBox_main_hobby1.getText());
        }
        if (checkBox_main_hobby2.isChecked()) {
            sb.append(checkBox_main_hobby2.getText());
        }
        if (checkBox_main_hobby3.isChecked()) {
            sb.append(checkBox_main_hobby3.getText());
        }
        if (checkBox_main_hobby4.isChecked()) {
            sb.append(checkBox_main_hobby4.getText());
        }
        return sb.toString();
    }
}
