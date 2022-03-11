package com.itbird.design.chain.upgrade;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by itbird on 2022/3/1
 */
public class InitUpgradeRequestHandler extends UpgradeRequestHandler {
    @Override
    boolean handleUpgradeRequest(UpgradeRequest request, UpgradeResultObservableAdapter adapter) {
        if (TextUtils.isEmpty(request.getPath())) {
            adapter.onError(UpgradeErrorCode.ZIP_PACKAGE_PARSE_EROOR);
            return true;
        }

        //解压压缩包，然后赋值相关参数
        request.setMcuPath("mcu path = kkkkkkkkk");
        request.setaSystemPath("a system path = kkkkkkkkk");
        request.setbSystemPath("b system path = kkkkkkkkk");
        Log.d(TAG(), "InitUpgradeRequestHandler handleUpgradeRequest 完成");
        return false;
    }
}
