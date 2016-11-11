package com.feicui.gaonews.biz;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.feicui.gaonews.adapter.NewsAdapter;
import com.feicui.gaonews.bean.ImageAsuncTaskListener;
import com.feicui.gaonews.utils.ImageManager;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 用AsyncTask异步加载图片
 */

public class ImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

    private ImageAsuncTaskListener imageAsuncTaskListener;

    private String UrlString;

    //接口回调
    public ImageAsyncTask(NewsAdapter newsAdapter) {
        this.imageAsuncTaskListener = (ImageAsuncTaskListener) newsAdapter;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(String... strings) {

        Bitmap bitmap = null;
        UrlString = strings[0];
        try {
            URL url = new URL(strings[0]);

            URLConnection con = url.openConnection();

            InputStream inputStream = con.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        if (bitmap != null) {
            //将url和图片放入缓存中
            ImageManager.getInstance().getCache().put(UrlString, bitmap);
            //将图片传入借口，以便进行接口回调
            imageAsuncTaskListener.getImageBitmap(bitmap);
        }

    }


}
