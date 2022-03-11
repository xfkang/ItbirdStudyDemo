package com.itbird.design.chain.upgrade;

import androidx.annotation.IntDef;

/**
 * Created by itbird on 2022/3/2
 */
@IntDef({UpgradeErrorCode.ZIP_PACKAGE_PARSE_EROOR})
public @interface UpgradeErrorCode {
    /**
     * ZIP 压缩包解压错误
     */
    int ZIP_PACKAGE_PARSE_EROOR = 1;
}
