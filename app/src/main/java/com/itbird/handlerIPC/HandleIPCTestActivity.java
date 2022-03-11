package com.itbird.handlerIPC;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.R;
import com.itbird.binder.MyMusicPlayService;

public class HandleIPCTestActivity extends AppCompatActivity {

    private static final String TAG = HandleIPCTestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_test);
        Log.e(TAG, TAG + " onCreate");
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, TAG + " onClick");
                Intent intent = new Intent(HandleIPCTestActivity.this, HandleIPCMusicPlayService.class);
                bindService(intent, serviceConnection, BIND_AUTO_CREATE );
            }
        });
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @SuppressLint("NewApi")
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Messenger messenger = new Messenger(service);
            Message message = Message.obtain();
            message.getData().putString("key" , "我是来自客户端的消息");
            try {
                messenger.send(message);
            } catch (RemoteException e) {
            }
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