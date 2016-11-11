package com.feicui.gaonews.utils;

import android.graphics.Bitmap;

import com.feicui.gaonews.db.ImageLruCache;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * 图片加载管理
 */

public class ImageManager {
    private static ImageManager manager;
    private ImageLruCache chche;
    private HashMap<String, SoftReference<Bitmap>> map = new HashMap<String, SoftReference<Bitmap>>();

    private ImageManager() {

    }

    /*
    * 单例模式
    * */
    public static ImageManager getInstance() {

        if (manager == null) {
            manager = new ImageManager();
        }
        return manager;
    }


    public ImageLruCache getCache() {

        int size = (int) Runtime.getRuntime().maxMemory();
        int maxsize = size / 8;
        if (chche == null) {
            chche = new ImageLruCache(maxsize, map);
        }

        return chche;
    }

    /*
    * 从缓存中取图片
    * */
    public Bitmap getImage(String key) {
        //从强引用中取图片
        Bitmap bitm = getCache().get(key);
        if (bitm != null) {
            return bitm;
        } else {
            //从软引用中取图片
            SoftReference<Bitmap> softbitmap = map.get(key);
            if (softbitmap != null) {
                Bitmap bitmap = softbitmap.get();

                chche.put(key, bitmap);
                return bitmap;
            } else {
                map.remove(key);
            }
        }
        return null;
    }


}
