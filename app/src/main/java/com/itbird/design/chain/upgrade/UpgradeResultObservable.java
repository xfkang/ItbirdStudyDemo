package com.itbird.design.chain.upgrade;

/**
 * Created by itbird on 2022/3/2
 */
public interface UpgradeResultObservable {
    void onError(int errorCode);

    void onUpgradeProgressCallback(int systemType, int process);
}
