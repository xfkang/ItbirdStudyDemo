package com.itbird.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.itbird.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by itbird on 2022/3/1
 */


public class HookTestActivity extends AppCompatActivity {

    private static final String TAG = HookTestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventbus_test);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HookTestActivity.this, TestNoActivity.class));
            }
        });
        try {
            hookButtonClick(button);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        hookStartActivtiy();
    }

    private void hookStartActivtiy() {
        try {
            //找到activity中想要替换的属性
            Field instrumentationField = Activity.class.getDeclaredField("mInstrumentation");
            instrumentationField.setAccessible(true);

            //拿到这个属性原有的值
            Instrumentation instrumentationreal = (Instrumentation) instrumentationField.get(this);

            //创建代理对象，并且把属性值替换为我们的代理对象
            HookInstumentation hookInstumentation = new HookInstumentation(instrumentationreal);
            instrumentationField.set(this, hookInstumentation);

            Log.d(TAG, "hookStartActivtiy");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void hookButtonClick(View view) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        //通过反射拿到指定的方法getListenerInfo
        Method getListenerInfoMethod = View.class.getDeclaredMethod("getListenerInfo");
        //暴力获取，防止方法为private、protected
        getListenerInfoMethod.setAccessible(true);
        //执行view的getListenerInfo方法，拿到返回值对象mListenerInfo
        Object mListenerInfo = getListenerInfoMethod.invoke(view);

        //得到原始的onclikListenr，由于上层调用不到ListenerInfo class，所以只能通过全类名，去反射获取类
        Class<?> viewListenerInfoClass = Class.forName("android.view.View$ListenerInfo");
        //获取ListenerInfo的属性mOnClickListener
        Field onClickListenerFileld = viewListenerInfoClass.getDeclaredField("mOnClickListener");
        onClickListenerFileld.setAccessible(true);
        //获取第一步获取到的mListenerInfo对象的，属性值，从而得到原始的listinfo的原始onclick事件
        View.OnClickListener origin = (View.OnClickListener) onClickListenerFileld.get(mListenerInfo);
        Log.d(TAG, origin.getClass().toString());

        //hook代理类替换原始onclick
        HookButtonOnclickListener onclickListener = new HookButtonOnclickListener(origin);
        onClickListenerFileld.set(mListenerInfo, onclickListener);
        Log.d(TAG, onClickListenerFileld.get(mListenerInfo).toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
