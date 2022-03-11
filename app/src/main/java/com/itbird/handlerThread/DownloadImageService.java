package com.itbird.handlerThread;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.itbird.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by itbird on 2022/2/3
 */
public class DownloadImageService extends IntentService {
    public static final String DOWNLOAD_URL = "url";
    public static final String INDEX_FLAG = "index_flag";
    private static final String TAG = DownloadImageService.class.getSimpleName();
    public static UpdateUI updateUI;


    public void setUpdateUI(UpdateUI updateUIInterface) {
        updateUI = updateUIInterface;
    }

    public DownloadImageService() {
        super("DownloadImageService");
    }

    /**
     * 实现异步任务的方法
     *
     * @param intent Activity传递过来的Intent,数据封装在intent中
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        //在子线程中进行网络请求
        Bitmap bitmap = downloadUrlBitmap(intent.getStringExtra(DOWNLOAD_URL));
        Message message = Message.obtain();
        message.what = IntentServiceTestActivity.MSG_DOWNLOAD_IMAGE_SUCCESS;
        message.obj = bitmap;
        //通知主线程去更新UI
        if (updateUI != null) {
            updateUI.updateUI(message);
        }
        Log.e(TAG, "onHandleIntent");
    }

    //----------------------重写一下方法仅为测试------------------------------------------
    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        super.onCreate();

//        Notification.Builder builder = new Notification.Builder(getApplicationContext(), AppEnv.UMENG_CHANNEL)
//                    .setContentTitle("正在后台运行")
//                .setSmallIcon(R.mipmap.ic_launcher);
//        startForeground(1, builder.build());
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e(TAG, "onStart");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return super.onBind(intent);
    }


    public interface UpdateUI {
        void updateUI(Message message);
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
