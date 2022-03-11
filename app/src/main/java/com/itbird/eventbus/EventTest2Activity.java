package com.itbird.eventbus;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.R;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by itbird on 2022/3/1
 */


public class EventTest2Activity extends AppCompatActivity {
    private static final String TAG = EventTest2Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventbus_test);
        Log.d(TAG, TAG);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventMsg("xxxxx"));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
