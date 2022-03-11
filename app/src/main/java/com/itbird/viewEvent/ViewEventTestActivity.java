package com.itbird.viewEvent;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.R;
import com.itbird.bitmapOOM.BitmapUtils;

public class ViewEventTestActivity extends AppCompatActivity {

    private String TAG = ViewEventTestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyLayout layout = (MyLayout) LayoutInflater.from(this).inflate(R.layout.view_event_test, null);
        setContentView(layout);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "MyLayout onTouch event = " + event.getAction());
                return false;
            }
        });
        MyView myView = findViewById(R.id.imageview);
        myView.setImageBitmap(BitmapUtils.decodeCalSampledBitmapFromResource(getResources(), R.drawable.bigpic, 300, 200));
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "MyView onClick");
            }
        });
    }
}