package com.itbird.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by itbird on 2022/2/28
 */
public class DiskImageCache implements ImageCache {
    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + url.substring(url.lastIndexOf("/")));
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + url.substring(url.lastIndexOf("/")));
        Log.d("itbird", file.getAbsolutePath());
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void remove(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }

        File file = new File("/sdcard/Pictures/" + url);
        if (file == null || !file.exists()) {
            return;
        }
        file.delete();
    }
}
