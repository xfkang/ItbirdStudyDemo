package com.itbird.mvp.google;

/**
 * Google Demo
 * Created by itbird on 2022/2/25
 */
public interface BaseView<T> {
    //View中，设置presenter对象，使View可以持有Presenter对象引用
    void setPresenter(T presenter);
}
