package com.feicui.gaonews.db;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * 加载图片缓存
 */

public class ImageLruCache extends LruCache<String,Bitmap> {

    private HashMap<String,SoftReference<Bitmap>> map  = new HashMap<String, SoftReference<Bitmap>>();

    public ImageLruCache(int maxSize, HashMap<String, SoftReference<Bitmap>> map) {
        super(maxSize);
        this.map=map;
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getByteCount();
    }

    @Override
    protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
        if (evicted) {
            SoftReference<Bitmap> soft = new SoftReference<Bitmap>(oldValue);
            map.put(key,soft);
        }
    }
}
