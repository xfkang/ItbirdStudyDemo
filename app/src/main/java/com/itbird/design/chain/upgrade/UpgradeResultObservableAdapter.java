package com.itbird.design.chain.upgrade;

import android.database.Observable;

/**
 * Created by itbird on 2022/3/2
 */
public class UpgradeResultObservableAdapter extends Observable<UpgradeResultObservable> implements UpgradeResultObservable {
    public void attatchUpgradeObserver(UpgradeResultObservable observer) {
        registerObserver(observer);
    }

    public void detachUpgradeObserver(UpgradeResultObservable observer) {
        unregisterObserver(observer);
    }

    @Override
    public void onError(int errorCode) {
        synchronized (mObservers) {
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onError(errorCode);
            }
        }
    }

    @Override
    public void onUpgradeProgressCallback(int systemType, int process) {
        synchronized (mObservers) {
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onUpgradeProgressCallback(systemType, process);
            }
        }
    }
}
