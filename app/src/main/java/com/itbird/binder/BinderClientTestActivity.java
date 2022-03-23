package com.itbird.binder;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.R;
import com.itbird.task.SecondTestActivity;

/**
 * binder实践
 * Created by itbird on 2022/1/31
 */
public class BinderClientTestActivity extends AppCompatActivity {

    private static final String TAG = BinderClientTestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_test);
        Log.e(TAG, TAG + " onCreate");
        Intent intent = new Intent(this, MyMusicPlayService.class);
        bindService(intent, serviceConnection, 0);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @SuppressLint("NewApi")
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBinder binder = service;
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            String url = "http://www.baidu.com";
            data.writeInterfaceToken("MyMusicPlayService");
            data.writeString(url);
            try {
                Log.e(TAG, "客户端调用服务端接口 start");
                binder.transact(1000, data, reply, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.e(TAG, "reply result = " + reply.readBoolean());
            data.recycle();
            reply.recycle();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onNewIntent(Intent intent) {
        Log.e(TAG, TAG + " onNewIntent");
        super.onNewIntent(intent);
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, TAG + " onDestroy");
        unbindService(serviceConnection);
        super.onDestroy();
    }
}