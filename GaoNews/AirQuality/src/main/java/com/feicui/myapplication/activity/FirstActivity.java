package com.feicui.myapplication.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.feicui.myapplication.R;
import com.feicui.myapplication.adapter.CityAdapter;
import com.feicui.myapplication.bean.PmCityInfo;
import com.feicui.myapplication.utils.HttpCilentGetOrPost;
import com.feicui.myapplication.utils.parsePMData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.feicui.myapplication.utils.HttpCilentGetOrPost.HttpGet;

public class FirstActivity extends AppCompatActivity {
    private ListView lv_city;
    private View show;

    private TextView close; //关闭
    private TextView city; //城市
    private TextView PM;//PM2.5指数
    private TextView AQI;//空气质量指数
    private TextView quality;//空气质量
    private TextView PM10;//PM10指数
    private TextView CO;//一氧化碳
    private TextView NO2;//二氧化氮
    private TextView O3;//臭氧
    private TextView SO2;//二氧化硫
    private TextView time;//更新时间
    private int postion;
    private String cityurl;
    private ArrayList<String> listname = new ArrayList<String>();
    private String cityname;//城市名
    private CityAdapter nameAdapter;
    private ArrayList<PmCityInfo> PMDataList = new ArrayList<PmCityInfo>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        initView();//初始化控件
        loadCityData();//加载城市名数据
        
        loadAirQualityData();
    }

    private void loadCityData() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                cityurl = "http://web.juhe.cn:8080/environment/air/airCities?key=48dd3260fce0fe28df8b250b98c46a8e";

                String cityData = HttpGet(cityurl);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(cityData);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        String namestr = object.getString("name");

                        listname.add(namestr);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        nameAdapter.setNameDataToAdapter(listname);
                        nameAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();


    }

    private void initView() {

        lv_city = (ListView) findViewById(R.id.lv_citylist);
        show = findViewById(R.id.show);
        close = (TextView) findViewById(R.id.close);//关闭
        city = (TextView) findViewById(R.id.city); //城市
        PM = (TextView) findViewById(R.id.PM);//PM2.5指数
        AQI = (TextView) findViewById(R.id.AQI);//空气质量指数
        quality = (TextView) findViewById(R.id.quality);//空气质量
        PM10 = (TextView) findViewById(R.id.PM10);//PM10指数
        CO = (TextView) findViewById(R.id.CO);//一氧化碳
        NO2 = (TextView) findViewById(R.id.NO2);//二氧化氮
        O3 = (TextView) findViewById(R.id.O3);//臭氧
        SO2 = (TextView) findViewById(R.id.SO2);//二氧化硫
        time = (TextView) findViewById(R.id.time);//更新时间
        nameAdapter = new CityAdapter(this);
        lv_city.setAdapter(nameAdapter);
        lv_city.setOnItemClickListener(onitemlistener);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show.setVisibility(View.GONE);
            }
        });
    }


    AdapterView.OnItemClickListener onitemlistener = new AdapterView.OnItemClickListener() {


        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//            show.setVisibility(View.INVISIBLE);
            cityname = listname.get(i);
            postion = i;
        }
    };

    private void ShowPMInformation(int i) {
        PmCityInfo pminfo = PMDataList.get(i);
        city.setText(pminfo.getName()); //城市
        PM.setText("PM2.5指数:" + pminfo.getPM());//PM2.5指数
        AQI.setText("空气质量指数:" + pminfo.getAQI());//空气质量指数
        quality.setText("空气质量:" + pminfo.getQuality());//空气质量
        PM10.setText("PM10指数:" + pminfo.getPM10());//PM10指数
        CO.setText("一氧化碳" + pminfo.getCO());//一氧化碳
        NO2.setText("二氧化氮" + pminfo.getNO2());//二氧化氮
        O3.setText("臭氧" + pminfo.getO3());//臭氧
        SO2.setText("二氧化硫" + pminfo.getSO2());//二氧化硫
        time.setText("更新时间" + pminfo.getTime());//更新时间

    }


    public void loadAirQualityData() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                String airPM = "http://web.juhe.cn:8080/environment/air/pm?city=" + cityname +
                        "&key=48dd3260fce0fe28df8b250b98c46a8e";
                String PMData = HttpCilentGetOrPost.HttpGet(airPM);
                parsePMData parse = new parsePMData(FirstActivity.this);
                final ArrayList<PmCityInfo> PMList = parse.parser(PMData);
                Log.e("PMList", PMList + "");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        PMDataList = PMList;
                    }
                });
            }
        }).start();

    }

}
