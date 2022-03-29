package com.itbird.mvp.my;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.itbird.R;


public class TaskMyActivity extends BaseActivity<TaskMyContract.View, TaskMyPresenter> implements TaskMyContract.View {

    private static final String TAG = TaskMyActivity.class.getSimpleName();
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp_view_test);
        mTextView = findViewById(R.id.textview);
        Log.e(TAG, TAG + " onCreate");

        mPresenter.loadDataFromModel();
    }

    @Override
    TaskMyPresenter initPresenter() {
        return new TaskMyPresenter();
    }


    @Override
    public void updateTextView(String s) {
        mTextView.setText(s);
    }
}