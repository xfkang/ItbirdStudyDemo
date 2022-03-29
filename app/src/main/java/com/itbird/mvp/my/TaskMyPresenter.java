package com.itbird.mvp.my;


public class TaskMyPresenter extends BasePresenter<TaskMyContract.View> implements TaskMyContract.Presenter {
    private static final String TAG = TaskMyPresenter.class.getSimpleName();

    @Override
    public void loadDataFromModel() {
        //TODO :loaddata,此处可以用model、或者进行业务操作
        //调用界面方法，进行数据刷新
        if (isViewAttached()) {
            getView().updateTextView("loaddata success!!!");
        }
    }
}