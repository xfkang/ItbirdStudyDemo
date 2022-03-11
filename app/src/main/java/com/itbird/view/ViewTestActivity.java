package com.itbird.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.R;
import com.itbird.task.SecondTestActivity;

public class ViewTestActivity extends AppCompatActivity {

    private static final String TAG = ViewTestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_test);
        Log.e(TAG, TAG + " onCreate");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.e(TAG, TAG + " onNewIntent");
        super.onNewIntent(intent);
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, TAG + " onDestroy");
        super.onDestroy();
    }
}