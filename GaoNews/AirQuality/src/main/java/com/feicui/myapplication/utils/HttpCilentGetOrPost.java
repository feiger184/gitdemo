package com.feicui.myapplication.utils;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/14 0014.
 */

public class HttpCilentGetOrPost {


    private static HttpClient mHttpClient;

    private static int Timeout = 5000;//超时时间
    private static int MaxTotalConnection = 8;//最大连接数

    public static synchronized HttpClient getHttpClient() {
        if (mHttpClient == null) {
            //参数对象
            HttpParams params = new BasicHttpParams();
            //设置连接池中超时时间
            ConnManagerParams.setTimeout(params, Timeout);
            //设置最大链接数
            ConnManagerParams.setMaxTotalConnections(params, MaxTotalConnection);
            //连接超时，定义通过网络与服务器建立连接的超时时间
            HttpConnectionParams.setConnectionTimeout(params, Timeout);
            mHttpClient = new DefaultHttpClient(params);
        }
        return mHttpClient;
    }

    /*
    * GET请求
    * */
    public static String HttpGet(String url) {


        try {
            HttpClient httpClient = getHttpClient();
            HttpGet request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            String resultStr = EntityUtils.toString(entity);
            return resultStr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    * POST请求
    * */
    public static String HttpPost(String url, String key, String value) {
        try {
            HttpClient httpClient = getHttpClient();
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair(key, value));
            HttpPost post = new HttpPost(url);
            post.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse response = httpClient.execute(post);
            String result = EntityUtils.toString(response.getEntity());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
