package com.itbird.imageloader;

import android.graphics.Bitmap;

/**
 * Created by itbird on 2022/2/28
 */
public interface ImageCache {
    Bitmap get(String url);

    void put(String url, Bitmap bitmap);

    void remove(String url);
}
