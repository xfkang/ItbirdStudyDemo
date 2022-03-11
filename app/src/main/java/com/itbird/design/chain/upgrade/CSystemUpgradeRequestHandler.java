package com.itbird.design.chain.upgrade;

import android.util.Log;

/**
 * Created by itbird on 2022/3/1
 */
public class CSystemUpgradeRequestHandler extends UpgradeRequestHandler {
    @Override
    boolean handleUpgradeRequest(UpgradeRequest request, UpgradeResultObservableAdapter adapter) {
        Log.d(TAG(), "CSystemUpgradeRequestHandler handleUpgradeRequest 完成");
        return false;
    }
}