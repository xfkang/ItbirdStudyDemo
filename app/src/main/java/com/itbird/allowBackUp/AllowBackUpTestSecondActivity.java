package com.itbird.allowBackUp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.R;

/**
 * allowBackup属性默认开启，备份应用数据，所以存在风险
 * 1.设置allowBackup为false
 * 2.进行设备唯一码校验，然后再使用备份或者清空备份
 * Created by itbird on 2022/1/31
 */
public class AllowBackUpTestSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allowbackup_test_second);
    }
}