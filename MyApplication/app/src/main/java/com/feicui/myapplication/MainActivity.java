package com.feicui.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private int[] imgIds = {R.drawable.meitu1, R.drawable.meitu2,
            R.drawable.meitu3, R.drawable.meitu4, R.drawable.meitu5,
            R.drawable.meitu6, R.drawable.meitu7, R.drawable.meitu8, };
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.imageView);


    }

   public void  clickButton(View v){

       switch(v.getId()){

           case R.id.left:
               index--;

           break;

           case R.id.right:
               index++;
               break;
       }

       if (index < 0) {
           index = imgIds.length - 1;
       }
       if (index > imgIds.length - 1) {
           index = 0;
       }
       iv.setImageResource(imgIds[index]);

   }
}
