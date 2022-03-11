package com.itbird.viewEvent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * Created by itbird on 2022/2/4
 */
public class MyLayout extends LinearLayout {
    private static final String TAG = MyLayout.class.getSimpleName();

    public MyLayout(Context context) {
        super(context);
    }

    public MyLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected boolean dispatchHoverEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //手指按下
                break;
            case MotionEvent.ACTION_MOVE:
                //手指移动
                break;
            case MotionEvent.ACTION_UP:
                //手指抬起
                break;
            case MotionEvent.ACTION_CANCEL:
                //取消触摸事件的传递
                break;
        }
        Log.e(TAG, "dispatchHoverEvent action = " + event.getAction());

        return super.dispatchHoverEvent(event);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        Log.e(TAG, "onInterceptTouchEvent action = " + ev.getAction());
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        Log.e(TAG, "onTouchEvent action = " + event.getAction());
        return super.onTouchEvent(event);
    }

}
