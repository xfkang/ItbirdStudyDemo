package com.itbird.mvp.my;


import java.lang.ref.WeakReference;

/**
 * Created by itbird on 2022/3/29
 */
public abstract class BasePresenter<V> {
    WeakReference<V> mIView;

    public void onAttach(V iView) {
        mIView = new WeakReference<>(iView);
    }

    public void onDetach() {
        mIView = null;
    }

    public V getView() {
        if (mIView != null) {
            mIView.get();
        }
        return null;
    }

    public boolean isViewAttached() {
        return mIView != null && mIView.get() != null;
    }
}
