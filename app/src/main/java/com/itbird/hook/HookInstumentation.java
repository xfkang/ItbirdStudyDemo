package com.itbird.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.IBinder;
import android.os.UserHandle;
import android.util.Log;

import com.itbird.eventbus.EventTest2Activity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by itbird on 2022/3/10
 */
public class HookInstumentation extends Instrumentation {
    Instrumentation instrumentation;
    String TAG = HookInstumentation.class.getSimpleName();

    public HookInstumentation(Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
    }

    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options) {
        try {
            Log.d(TAG, "HookInstumentation");
            //这里把intent 中 target替换为我们想要启动的activity
            intent.setClass(target, EventTest2Activity.class);
            Method excc = Instrumentation.class.getDeclaredMethod("execStartActivity", Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, int.class, Bundle.class);
            excc.setAccessible(true);
            return (ActivityResult) excc.invoke(instrumentation, who, contextThread, token, target, intent, requestCode, options);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
