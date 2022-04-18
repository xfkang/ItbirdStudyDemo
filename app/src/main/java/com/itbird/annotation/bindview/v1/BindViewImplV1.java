package com.itbird.annotation.bindview.v1;

import android.app.Activity;
import android.text.TextUtils;
import com.itbird.bindview_annotation.ItbirdRunningBinderView;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 运行时解析注解，反射调用相关方法
 * Created by itbird on 2022/4/12
 */
public class BindViewImplV1 {
    private static final String METHOD_NAME_SET_CONTENTVIEW = "setContentView";
    private static final String METHOD_NAME_FIIND_VIEW_BY_ID = "findViewById";
    private static final String TAG = BindViewImplV1.class.getSimpleName();

    /**
     * @param activity
     */
    public static void bind(Activity activity) {
        //获取类
        Class activityClass = activity.getClass();
        //判断类是否有注解
        if (activityClass.isAnnotationPresent(ItbirdRunningBinderView.class)) {
            //获取activity的类注解 ItbirdBinderView
            ItbirdRunningBinderView annotation = (ItbirdRunningBinderView) activityClass.getAnnotation(ItbirdRunningBinderView.class);
            //获取注解的值
            int layoutid = annotation.value();
            //reflectMethod setContentView invoke
            reflectMethod(activity, METHOD_NAME_SET_CONTENTVIEW, new Object[]{layoutid}, new Class[]{int.class});
        }

        //获取所有的成员变量
        Field[] fields = activityClass.getDeclaredFields();
        //遍历成员成员是否有ItbirdBinderView注解
        for (Field field : fields) {
            if (field.isAnnotationPresent(ItbirdRunningBinderView.class)) {
                //对属性设置访问权限  当类中的成员变量为private时 必须设置此项
                field.setAccessible(true);
                //获取activity的类注解 ItbirdBinderView
                ItbirdRunningBinderView annotation = field.getAnnotation(ItbirdRunningBinderView.class);
                //获取注解的值
                int viewID = annotation.value();
                //reflectMethod findViewById invoke
                Object result = reflectMethod(activity, METHOD_NAME_FIIND_VIEW_BY_ID, new Object[]{viewID}, new Class[]{int.class});
                try {
                    field.set(activity, result);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 反射调用某个类的某个方法
     *
     * @param src        对象
     * @param methodName 方法名
     * @param objects    参数列表
     */
    private static Object reflectMethod(Object src, String methodName, Object[] objects, Class<?>... classes) {
        if (src == null || TextUtils.isEmpty(methodName)) {
            return null;
        }
        try {
            Method method = src.getClass().getMethod(methodName, classes);
            return method.invoke(src, objects);
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
