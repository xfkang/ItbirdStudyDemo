package com.itbird.design.chain;

/**
 * Created by itbird on 2022/3/1
 */
public abstract class Handler {
    Handler next;

    void setNext(Handler next) {
        this.next = next;
    }

    String TAG() {
        return getClass().getSimpleName();
    }

    abstract void handlerRequeset(Request requestH);
}
