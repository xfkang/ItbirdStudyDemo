package com.itbird.handlerThread;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.itbird.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class IntentServiceTestActivity extends AppCompatActivity {
    // UI主线程的handler和消息定义
    private Handler mUIHandler = null;
    public static final int MSG_DOWNLOAD_IMAGE_SUCCESS = 1;
    // ImageView控件，用于显示下载的图片
    private ImageView mImageView;
    /**
     * 图片地址集合
     */
    private final String url[] = {
            "https://img-blog.csdn.net/20160903083245762",
            "https://img-blog.csdn.net/20160903083252184",
            "https://img-blog.csdn.net/20160903083257871",
            "https://img-blog.csdn.net/20160903083257871",
            "https://img-blog.csdn.net/20160903083311972",
            "https://img-blog.csdn.net/20160903083319668",
            "https://img-blog.csdn.net/20160903083326871"
    };
    Intent mStartServiceintent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageviewl_load_bitmap_test);
        mImageView = findViewById(R.id.imageview);

        mUIHandler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg == null) {
                    return;
                }

                //UI主线程，显示图片
                if (msg.what == MSG_DOWNLOAD_IMAGE_SUCCESS) {
                    mImageView.setImageBitmap((Bitmap) msg.obj);
                }
            }
        };
        DownloadImageService intentService = new DownloadImageService();
        intentService.setUpdateUI(new DownloadImageService.UpdateUI() {
            @Override
            public void updateUI(Message message) {
                mUIHandler.sendMessageDelayed(message,1000);
            }
        });
        mStartServiceintent = new Intent(this, intentService.getClass());
        for (int i = 0; i < url.length; i++) {
            mStartServiceintent.putExtra("url", url[i]);
            startForegroundService(mStartServiceintent);
        }
    }

    @Override
    protected void onDestroy() {
        stopService(mStartServiceintent);
        super.onDestroy();
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