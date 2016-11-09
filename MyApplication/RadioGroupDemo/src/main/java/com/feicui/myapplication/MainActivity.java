package com.feicui.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup_main_sex;
    private Button button_main_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button_main_submit = (Button) findViewById(R.id.button_main_submit);
        radioGroup_main_sex = (RadioGroup) findViewById(R.id.radioGroup_main_sex);
        button_main_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取勾选项的id
                int id = radioGroup_main_sex.getCheckedRadioButtonId();
                // 通过id找到被勾选项的控件
                RadioButton radioButton = (RadioButton) findViewById(id);
                // 通过控件的getText()方法找到该控件的text属性的值
                String result = radioButton.getText().toString();
                Toast.makeText(MainActivity.this, "您选择了：" + result,
                        Toast.LENGTH_SHORT).show();
            }
        });

        radioGroup_main_sex
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton radioButton = (RadioButton) findViewById(checkedId);
                        String result = radioButton.getText().toString();
                        Toast.makeText(MainActivity.this, "您选择了：" + result,
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
