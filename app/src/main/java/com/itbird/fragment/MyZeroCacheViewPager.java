package com.itbird.fragment;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by itbird on 2022/3/22
 */
public class MyZeroCacheViewPager extends ViewPager {
    public MyZeroCacheViewPager(@NonNull Context context) {
        super(context);
    }

    public MyZeroCacheViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setOffscreenPageLimit(int limit) {
    }
}
