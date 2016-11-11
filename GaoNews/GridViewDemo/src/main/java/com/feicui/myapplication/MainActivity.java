package com.feicui.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gv = (GridView) findViewById(R.id.gridview);
        int[] a = new int[]{R.drawable.meitu1, R.drawable.meitu2, R.drawable.meitu3, R.drawable.meitu4,
                R.drawable.meitu5, R.drawable.meitu6, R.drawable.meitu7, R.drawable.meitu8};

        myAdapter adaptr = new myAdapter(this, a);
        gv.setAdapter(adaptr);
    }
}
