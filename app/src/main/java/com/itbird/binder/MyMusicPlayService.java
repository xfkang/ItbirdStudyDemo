package com.itbird.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.itbird.music.IMusicFuction;

/**
 * Created by itbird on 2022/2/10
 */
public class MyMusicPlayService extends Service {
    String TAG = MyMusicPlayService.class.getSimpleName();

    IMusicFuction.Stub fuction = new IMusicFuction.Stub() {
        @Override
        public boolean start(String url) throws RemoteException {
            return false;
        }

        @Override
        public boolean stop() throws RemoteException {
            return false;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return fuction;
    }
}
