package com.itbird;

import android.app.Application;

import com.itbird.eventbus.MyEventBusIndex;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by itbird on 2022/2/28
 */
public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
    }
}
