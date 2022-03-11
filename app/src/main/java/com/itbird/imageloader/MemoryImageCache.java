package com.itbird.imageloader;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by itbird on 2022/2/28
 */
public class MemoryImageCache implements ImageCache {
    LruCache<String, Bitmap> bitmapLruCache;

    public MemoryImageCache() {
        bitmapLruCache = new LruCache<>(10);
    }

    @Override
    public Bitmap get(String url) {
        return bitmapLruCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        bitmapLruCache.put(url, bitmap);
    }

    @Override
    public void remove(String url) {
        bitmapLruCache.remove(url);
    }
}
