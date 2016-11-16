package com.feicui.myapplication.utils;


import android.content.Context;

import com.feicui.myapplication.bean.PmCityInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 解析
 */

public class parsePMData {

    private Context context;

    public parsePMData() {
    }

    public parsePMData(Context context) {
        this.context = context;
    }


    /*
    * 解析数据
    * */
    public ArrayList<PmCityInfo> parser(String dataStr) {

        ArrayList<PmCityInfo> PMList = new ArrayList<PmCityInfo>();
        try {
            JSONObject jsonObject = new JSONObject(dataStr);
            JSONArray jsonArray = jsonObject.getJSONArray("result");


            for (int j=0; j<jsonArray.length(); j++) {
                JSONObject object = jsonArray.getJSONObject(j);
                String city = object.getString("city");
                int PM = object.getInt("PM2.5");
                int AQI = object.getInt("AQI");
                String quality = object.getString("quality");
                int PM10 = object.getInt("PM10");
                int CO = object.getInt("CO");
                int NO2 = object.getInt("NO2");
                int o3 = object.getInt("O3");
                int SO2 = object.getInt("SO2");
                String time = object.getString("time");
                PmCityInfo newsInfo = new PmCityInfo(city, PM, AQI, quality,PM10,CO, NO2, o3, SO2, time);
                PMList.add(newsInfo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return PMList;
    }
}
