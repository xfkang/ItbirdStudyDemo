package com.itbird.mvp.my;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * Activity基类
 * Created by itbird on 2022/2/25
 */
public abstract class BaseActivity extends Activity implements IView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        initPresenter();
        initView();
    }

    /**
     * 界面布局id
     */
    public abstract int getLayoutID();

    /**
     * 开发者只需关注，去在此方法中，实现presenter初始化
     */
    public abstract void initPresenter();

    /**
     * 控件初始化
     */
    public abstract void initView();
}
