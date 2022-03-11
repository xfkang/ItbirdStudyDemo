package com.itbird.mvp.my;

/**
 * my Demo
 * Created by itbird on 2022/2/25
 */
public interface TaskMyContract {
    interface View extends IView {
        //界面UI刷新方法
        void updateTextView(String s);
    }

    interface Presenter extends IPresenter {
        void loadDataFromModel();
    }
}
