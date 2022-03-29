package com.itbird.mvp.my;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;


/**
 * Created by itbird on 2022/3/29
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends Activity {
    T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.onAttach((V) this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
            mPresenter = null;
        }
    }

    public T getPresenter() {
        return mPresenter;
    }


    abstract T initPresenter();
}
