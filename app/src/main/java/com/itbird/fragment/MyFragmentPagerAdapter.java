package com.itbird.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * Created by itbird on 2022/3/18
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 4;
    Fragment fragment1;
    Fragment fragment2;
    Fragment fragment3;
    Fragment fragment4;

    public MyFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        fragment1 = BlankFragment1.newInstance("name1", "age1");
        fragment2 = BlankFragment2.newInstance("name2", "age2");
        fragment3 = BlankFragment3.newInstance("name3", "age3");
        fragment4 = BlankFragment4.newInstance("name4", "age4");
    }

    public MyFragmentPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragment1 = BlankFragment1.newInstance("name1", "age1");
        fragment2 = BlankFragment2.newInstance("name2", "age2");
        fragment3 = BlankFragment3.newInstance("name3", "age3");
        fragment4 = BlankFragment4.newInstance("name4", "age4");
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = fragment1;
                break;
            case 1:
                fragment = fragment2;
                break;
            case 2:
                fragment = fragment3;
                break;
            case 3:
                fragment = fragment4;
                break;
        }
        return fragment;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return super.isViewFromObject(view, object);
    }
}
