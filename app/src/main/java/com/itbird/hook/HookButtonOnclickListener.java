package com.itbird.hook;

import android.util.Log;
import android.view.View;

/**
 * Created by itbird on 2022/3/10
 */
public class HookButtonOnclickListener implements View.OnClickListener {
    String TAG = HookButtonOnclickListener.class.getSimpleName();
    View.OnClickListener onClickListener;

    public HookButtonOnclickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "my click");
        if (onClickListener != null) {
            onClickListener.onClick(v);
        }
    }
}
