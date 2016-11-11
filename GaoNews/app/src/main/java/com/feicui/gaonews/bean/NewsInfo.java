package com.feicui.gaonews.bean;

import java.io.Serializable;

/**
 * NewsInfo方法
 */

public class NewsInfo implements Serializable {
    /* 新闻id*/
    private int nid;
    /* 标题*/
    private String title;
    /*摘要*/
    private String summary;
    /*图标*/
    private String icon;
    /*网络链接*/
    private String link;
    /*新闻类型*/
    private int type;
    /*时间*/
    private String stamp;

    //构造函数
    public NewsInfo(int nid, String title, String summary, String icon, String link, int type, String stamp) {
        this.nid = nid;
        this.title = title;
        this.summary = summary;
        this.icon = icon;
        this.link = link;
        this.type = type;
        this.stamp = stamp;
    }

    public NewsInfo() {

    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    @Override
    public String toString() {
        return "NewsInfo{" +
                "nid=" + nid +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", icon='" + icon + '\'' +
                ", link='" + link + '\'' +
                ", type=" + type +
                ", stamp='" + stamp + '\'' +
                '}';
    }
}
