package com.itbird.allowBackUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.itbird.R;

/**
 * allowBackup属性默认开启，备份应用数据，所以存在风险
 * 1.设置allowBackup为false
 * 2.进行设备唯一码校验，然后再使用备份或者清空备份
 * Created by itbird on 2022/1/31
 */
public class AllowBackUpTestLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allowbackup_test_login);
        EditText name = findViewById(R.id.name);
        EditText passwd = findViewById(R.id.passwd);
        SharedPreferences sp = getSharedPreferences("information",MODE_PRIVATE);
        if (!TextUtils.isEmpty(sp.getString("name", null)))   {
            name.setText(sp.getString("name", null));
        }

        if (!TextUtils.isEmpty(sp.getString("passwd", null)))   {
            passwd.setText(sp.getString("passwd", null));
        }

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(passwd.getText())) {
                    Toast.makeText(AllowBackUpTestLoginActivity.this, "请输入账号和密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name", name.getText().toString());
                editor.putString("passwd", passwd.getText().toString());
                editor.commit();//进行提交
                Intent intent = new Intent(AllowBackUpTestLoginActivity.this, AllowBackUpTestSecondActivity.class);
                startActivity(intent);
            }
        });
    }
}