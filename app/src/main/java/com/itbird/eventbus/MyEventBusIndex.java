package com.itbird.eventbus;

import org.greenrobot.eventbus.meta.SimpleSubscriberInfo;
import org.greenrobot.eventbus.meta.SubscriberInfo;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;
import org.greenrobot.eventbus.meta.SubscriberMethodInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by itbird on 2022/4/7
 */
public class MyEventBusIndex implements SubscriberInfoIndex {
    private static final Map<Class<?>, SubscriberInfo> SUBSCRIBER_INDEX;

    static {
        SUBSCRIBER_INDEX = new HashMap<Class<?>, SubscriberInfo>();

        putIndex(new SimpleSubscriberInfo(EventTestActivity.class, true, new SubscriberMethodInfo[] {
                new SubscriberMethodInfo("onMsgEvent", EventMsg.class),
        }));
    }

    private static void putIndex(SubscriberInfo info) {
        SUBSCRIBER_INDEX.put(info.getSubscriberClass(), info);
    }

    @Override
    public SubscriberInfo getSubscriberInfo(Class<?> subscriberClass) {
        SubscriberInfo info = SUBSCRIBER_INDEX.get(subscriberClass);
        if (info != null) {
            return info;
        } else {
            return null;
        }
    }
}
