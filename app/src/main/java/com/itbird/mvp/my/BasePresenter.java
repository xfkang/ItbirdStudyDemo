package com.itbird.mvp.my;

/**
 * Presenter基类
 * Created by itbird on 2022/2/25
 */
public abstract class BasePresenter<V extends IView> implements IPresenter {
    V mView;

    /**
     * 绑定view，一般在初始化中调用该方法
     */
    @Override
    public void onAttach(IView view) {
        mView = (V) view;
    }

    /**
     * 断开view，一般在onDestroy中调用
     */
    @Override
    public void onDetach() {
        mView = null;
    }

    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    public boolean isViewAttached() {
        return mView != null;
    }
}
