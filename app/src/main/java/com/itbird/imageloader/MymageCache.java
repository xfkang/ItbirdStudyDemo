package com.itbird.imageloader;

import android.graphics.Bitmap;

/**
 * Created by itbird on 2022/2/28
 */
public class MymageCache implements ImageCache {
    MemoryImageCache memoryImageCache;
    DiskImageCache diskImageCache;

    public MymageCache() {
        memoryImageCache = new MemoryImageCache();
        diskImageCache = new DiskImageCache();
    }

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = memoryImageCache.get(url);
        if (bitmap != null) {
            return bitmap;
        }

        bitmap = diskImageCache.get(url);
        if (bitmap != null) {
            return bitmap;
        }
        return null;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        memoryImageCache.put(url, bitmap);
        diskImageCache.put(url, bitmap);
    }

    @Override
    public void remove(String url) {
        memoryImageCache.remove(url);
        diskImageCache.remove(url);
    }
}
