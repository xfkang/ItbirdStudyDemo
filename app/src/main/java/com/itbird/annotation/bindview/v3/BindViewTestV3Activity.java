package com.itbird.annotation.bindview.v3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.R;
import com.itbird.bindview.annotation.ItbirdAopBinderView;
import com.itbird.bindview.annotation.ItbirdOnclick;

/**
 * 运行时注解+反射实现 view ioc框架
 * 事件自动注册
 */
@ItbirdAopBinderView(R.layout.bindview_test)
public class BindViewTestV3Activity extends AppCompatActivity {
    private static final String TAG = BindViewTestV3Activity.class.getSimpleName();

    @ItbirdAopBinderView(R.id.button)
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bindview_test);
        //关键的代码，通过反射，解析AOP生成的类
        BindViewImplV3.bind(this);
    }

    @ItbirdOnclick(R.id.button)
    public void onItbirdClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                Toast.makeText(this, "button on click", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}