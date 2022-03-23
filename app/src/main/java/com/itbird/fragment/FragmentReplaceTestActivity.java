package com.itbird.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.itbird.R;

import java.util.List;

public class FragmentReplaceTestActivity extends FragmentActivity implements View.OnClickListener {

    private static final String TAG = FragmentReplaceTestActivity.class.getSimpleName();
    Fragment fragment1;
    Fragment fragment2;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment__dynamic_test);
        Log.e(TAG, TAG + " onCreate");
        fragmentManager = getSupportFragmentManager();
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        fragment1 = BlankFragment1.newInstance("name1", "age1");
        fragment2 = BlankFragment2.newInstance("name2", "age2");
        findViewById(R.id.button1).performClick();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.button1:
                Log.d(TAG, "fragment1 = " + fragment1);
                transaction.replace(R.id.fragment, fragment1);
                break;
            case R.id.button2:
                Log.d(TAG, "fragment2 = " + fragment2);
                transaction.replace(R.id.fragment, fragment2);
                break;
        }
        transaction.commit();
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, TAG + " onDestroy");
        super.onDestroy();
    }
}