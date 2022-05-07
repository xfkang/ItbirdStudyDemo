package com.itbird.annotation.bindview.v2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.R;
import com.itbird.bindview.annotation.ItbirdAopBinderView;

/**
 * 编译注解+APT+JAVAPoet+反射实现 view ioc框架
 */
@ItbirdAopBinderView(R.layout.bindview_test)
public class BindViewTestV2Activity extends AppCompatActivity {
    private static final String TAG = BindViewTestV2Activity.class.getSimpleName();

    @ItbirdAopBinderView(R.id.textview1)
    TextView textView1;

    @ItbirdAopBinderView(R.id.textview2)
    TextView textView2;

    @ItbirdAopBinderView(R.id.textview3)
    TextView textView3;

    @ItbirdAopBinderView(R.id.textview4)
    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //关键的代码，通过反射，解析AOP生成的类
        BindViewImplV2.bind(this);
        if (textView1 != null) {
            textView1.setText(getResources().getString(R.string.bindview_text1));
        }
        if (textView2 != null) {
            textView2.setText(getResources().getString(R.string.bindview_text2));
        }
        if (textView3 != null) {
            textView3.setText(getResources().getString(R.string.bindview_text3));
        }
        if (textView4 != null) {
            textView4.setText(getResources().getString(R.string.bindview_text4));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}