package com.itbird.design.chain.upgrade;

/**
 * Created by itbird on 2022/3/1
 */
public class UpgradeManager {
    UpgradeResultObservableAdapter mAdapter;

    final static class UpgradeManagerHolder {
        final static UpgradeManager mInstance = new UpgradeManager();
    }

    public final static UpgradeManager getInstance() {
        return UpgradeManagerHolder.mInstance;
    }

    UpgradeManager() {
        mAdapter = new UpgradeResultObservableAdapter();
    }

    /**
     * 升级包路径
     *
     * @param path
     */
    public void startUpgrade(String path) {
        UpgradeRequest request = new UpgradeRequest(path);
        InitUpgradeRequestHandler initUpgradeRequestHandler = new InitUpgradeRequestHandler();
        ASystemUpgradeRequestHandler aSystemUpgradeRequestHandler = new ASystemUpgradeRequestHandler();
        BSystemUpgradeRequestHandler bSystemUpgradeRequestHandler = new BSystemUpgradeRequestHandler();
        CSystemUpgradeRequestHandler cSystemUpgradeRequestHandler = new CSystemUpgradeRequestHandler();
        initUpgradeRequestHandler.setNextHandler(aSystemUpgradeRequestHandler);
        aSystemUpgradeRequestHandler.setNextHandler(bSystemUpgradeRequestHandler);
        bSystemUpgradeRequestHandler.setNextHandler(cSystemUpgradeRequestHandler);
        initUpgradeRequestHandler.handlerRequest(request, mAdapter);
    }
}
