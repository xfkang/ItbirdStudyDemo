package com.itbird.mvp.google;

public class TaskGooglePresenter implements TaskDetailContract.Presenter {
    private static final String TAG = TaskGooglePresenter.class.getSimpleName();
    TaskDetailContract.View mView;

    public TaskGooglePresenter(TaskDetailContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void loadDataFromModel() {
        //TODO :loaddata,此处可以用model、或者进行业务操作
        //调用界面方法，进行数据刷新
        mView.updateTextView("loaddata success!!!");
    }
}