package com.feicui.myapplication.bean;

/**
 * Created by Administrator on 2016/11/14 0014.
 */

public class PmCityInfo {
    private String city; //城市
    private int PM;//PM2.5指数
    private int AQI;//空气质量指数
    private String quality;//空气质量
    private int PM10;//PM10指数
    private int CO;//一氧化碳
    private int NO2;//二氧化氮
    private int O3;//臭氧
    private int SO2;//二氧化硫
    private String  time;//更新时间


    public PmCityInfo(String name, int PM, int AQI, String quality, int PM10,int CO, int NO2, int o3, int SO2, String time) {
        this.city = city;
        this.PM = PM;
        this.AQI = AQI;
        this.quality = quality;
        this.CO = CO;
        this.NO2 = NO2;
        O3 = o3;
        this.SO2 = SO2;
        this.time = time;
    }



    public int getPM10() {
        return PM10;
    }

    public void setPM10(int PM10) {
        this.PM10 = PM10;
    }
    public String getName() {
        return city;
    }

    public void setName(String name) {
        this.city = name;
    }

    public int getPM() {
        return PM;
    }

    public void setPM(int PM) {
        this.PM = PM;
    }

    public int getAQI() {
        return AQI;
    }

    public void setAQI(int AQI) {
        this.AQI = AQI;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public int getCO() {
        return CO;
    }

    public void setCO(int CO) {
        this.CO = CO;
    }

    public int getNO2() {
        return NO2;
    }

    public void setNO2(int NO2) {
        this.NO2 = NO2;
    }

    public int getO3() {
        return O3;
    }

    public void setO3(int o3) {
        O3 = o3;
    }

    public int getSO2() {
        return SO2;
    }

    public void setSO2(int SO2) {
        this.SO2 = SO2;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "PmCityInfo{" +
                "city='" + city + '\'' +
                ", PM=" + PM +
                ", AQI=" + AQI +
                ", quality='" + quality + '\'' +
                ", PM10=" + PM10 +
                ", CO=" + CO +
                ", NO2=" + NO2 +
                ", O3=" + O3 +
                ", SO2=" + SO2 +
                ", time='" + time + '\'' +
                '}';
    }
}
