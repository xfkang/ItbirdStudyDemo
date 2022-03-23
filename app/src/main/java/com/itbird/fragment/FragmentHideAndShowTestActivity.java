package com.itbird.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.itbird.R;

import java.util.List;

/**
 *
 */
public class FragmentHideAndShowTestActivity extends FragmentActivity implements View.OnClickListener, BlankFragment1.ActivityListener {

    private static final String TAG = FragmentHideAndShowTestActivity.class.getSimpleName();
    BlankFragment1 fragment1;
    BlankFragment2 fragment2;
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
        fragment1.setActivityListener(this);
        fragment2 = BlankFragment2.newInstance("name2", "age2");
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.button1:
                Log.d(TAG, "fragment1 = " + fragment1);
                hideAllFragment();
                if (fragment1.isAdded()) {
                    transaction.show(fragment1);
                } else {
                    transaction.add(R.id.fragment, fragment1);
                }
                break;
            case R.id.button2:
                Log.d(TAG, "fragment2 = " + fragment2);
                hideAllFragment();
                if (fragment2.isAdded()) {
                    transaction.show(fragment2);
                } else {
                    transaction.add(R.id.fragment, fragment2);
                }
                break;
        }
        transaction.commit();
    }


    private void hideAllFragment() {
        List<Fragment> fragments = fragmentManager.getFragments();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (Fragment fragment : fragments) {
            transaction.hide(fragment);
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

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}