package com.itbird.mvp.my;

import com.itbird.R;

public class TaskMyActivity extends BaseActivity implements TaskMyContract.View {

    private static final String TAG = TaskMyActivity.class.getSimpleName();
    TaskMyPresenter mPresenter;

    @Override
    public int getLayoutID() {
        return R.layout.mvp_view_test;
    }

    @Override
    public void initPresenter() {
        mPresenter = new TaskMyPresenter();
        mPresenter.onAttach(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void updateTextView(String s) {

    }
}