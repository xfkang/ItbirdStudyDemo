package com.itbird.handlerIPC;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;

import com.itbird.music.IMusicFuction;

/**
 * Created by itbird on 2022/2/10
 */
public class HandleIPCMusicPlayService extends Service {
    String TAG = HandleIPCMusicPlayService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "HandleIPCMusicPlayServiceonCreate" );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Messenger messenger = new Messenger(new Handler(getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg != null) {
                    Log.d(TAG, "handleMessage msg.data =" + msg.getData().getString("key"));
                }
                super.handleMessage(msg);
            }
        });
        return messenger.getBinder();
    }
}
