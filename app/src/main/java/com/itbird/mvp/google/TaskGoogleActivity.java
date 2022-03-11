package com.itbird.mvp.google;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.R;

public class TaskGoogleActivity extends AppCompatActivity implements TaskDetailContract.View {

    private static final String TAG = TaskGoogleActivity.class.getSimpleName();
    private TaskDetailContract.Presenter mPresenter;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp_view_test);
        findViewById(R.id.textview);
        Log.e(TAG, TAG + " onCreate");
        new TaskGooglePresenter(this);
    }

    @Override
    public void setPresenter(TaskDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void updateTextView(String s) {
        mTextView.setText(s);
    }
}