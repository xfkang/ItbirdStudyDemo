package com.itbird.fragment;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.itbird.R;

import java.util.List;

/**
 *
 */
public class FragmenViewPagerTestActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    private static final String TAG = FragmenViewPagerTestActivity.class.getSimpleName();
    ViewPager viewPager;
    FragmentManager fragmentManager;
    MyFragmentPagerAdapter myFragmentPagerAdapter;
    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment__viewpager_test);
        Log.e(TAG, TAG + " onCreate");
        fragmentManager = getSupportFragmentManager();
        viewPager = findViewById(R.id.viewpager);
        radioGroup = findViewById(R.id.line1);
        radioGroup.setOnCheckedChangeListener(this);
//        myFragmentPagerAdapter = new MyFragmentPagerAdapter(fragmentManager);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);
        radioButton1 = findViewById(R.id.button1);
        radioButton1.setOnClickListener(this);
        radioButton2 = findViewById(R.id.button2);
        radioButton2.setOnClickListener(this);
        radioButton3 = findViewById(R.id.button3);
        radioButton3.setOnClickListener(this);
        radioButton4 = findViewById(R.id.button4);
        radioButton4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Log.d(TAG, "fragment1");
                viewPager.setCurrentItem(0);
                break;
            case R.id.button2:
                Log.d(TAG, "fragment2");
                viewPager.setCurrentItem(1);
                break;
            case R.id.button3:
                Log.d(TAG, "fragment3");
                viewPager.setCurrentItem(2);
                break;
            case R.id.button4:
                Log.d(TAG, "fragment4");
                viewPager.setCurrentItem(3);
                break;
        }
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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (viewPager.getCurrentItem()) {
                case 0:
                    radioButton1.setChecked(true);
                    break;
                case 1:
                    radioButton2.setChecked(true);
                    break;
                case 2:
                    radioButton3.setChecked(true);
                    break;
                case 3:
                    radioButton4.setChecked(true);
                    break;
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.button1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.button2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.button3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.button4:
                viewPager.setCurrentItem(3);
                break;
        }
    }
}