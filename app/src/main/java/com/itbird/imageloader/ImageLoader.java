package com.itbird.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by itbird on 2022/2/28
 */
public class ImageLoader {
    private volatile static ImageLoader mInstance;
    private ImageCache mImageCache;
    ExecutorService mExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private WeakReference<Context> mContext;
    private Handler mHandler;

    public static ImageLoader getInstance(Context context) {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader(context);
                }
            }
        }
        return mInstance;
    }

    private ImageLoader(Context context) {
        mContext = new WeakReference<>(context);
        mHandler = new Handler(context.getMainLooper());
    }

    public void setImageLCache(ImageCache imageLCache) {
        mImageCache = imageLCache;
    }


    public void setImageView(String url, ImageView imageView) {
        if (TextUtils.isEmpty(url) || imageView == null) {
            return;
        }
        Bitmap bitmap = mImageCache.get(url);
        if (bitmap == null) {
            imageView.setTag(url);
            downloadImage(url, imageView);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    private void downloadImage(String url, ImageView imageView) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadUrlBitmap(url);
                if (TextUtils.equals((CharSequence) imageView.getTag(), url)) {
                    Log.e("itbird", "" + bitmap.getByteCount());
                    //cackback主线程
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bitmap);
                        }
                    });
                }
                if (mImageCache != null) {
                    mImageCache.put(url, bitmap);
                }
            }
        });
    }

    private Bitmap downloadUrlBitmap(String urlString) {
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        Bitmap bitmap = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            bitmap = BitmapFactory.decodeStream(in);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            byte[] bytes = bos.toByteArray();
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }
}
