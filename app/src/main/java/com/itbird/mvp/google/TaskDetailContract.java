package com.itbird.mvp.google;

/**
 * Google Demo
 * Created by itbird on 2022/2/25
 */
public interface TaskDetailContract {
    interface View extends BaseView<Presenter> {
        //界面UI刷新方法
        void updateTextView(String s);
    }

    interface Presenter extends BasePresenter {
        void loadDataFromModel();
    }
}
