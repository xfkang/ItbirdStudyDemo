package com.itbird.design.chain.upgrade;

/**
 * Created by itbird on 2022/3/1
 */
public abstract class UpgradeRequestHandler {
    private UpgradeRequestHandler nextHandler;

    public void setNextHandler(UpgradeRequestHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * 模板模式，内部定义了执行顺序
     *
     * @param request
     */
    public final void handlerRequest(UpgradeRequest request, UpgradeResultObservableAdapter adapter) {
        if (handleUpgradeRequest(request, adapter)) {
            return;
        }

        if (nextHandler != null) {
            nextHandler.handlerRequest(request, adapter);
        }
    }

    public final String TAG() {
        return getClass().getSimpleName();
    }

    /**
     * 交由具体的请求处理者实现
     *
     * @param request
     * @return
     */
    abstract boolean handleUpgradeRequest(UpgradeRequest request, UpgradeResultObservableAdapter adapter);
}
