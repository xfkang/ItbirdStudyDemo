package com.itbird.design.chain;

import android.util.Log;

/**
 * Created by itbird on 2022/3/1
 */
public class RequestHandler3 extends Handler {
    @Override
    void handlerRequeset(Request request) {
        Log.d(TAG(), "RequestHandler3 handlerRequeset" + request.getUrl());
        if (next != null) {
            next.handlerRequeset(request);
        }
    }
}
