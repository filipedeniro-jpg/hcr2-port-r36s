package com.google.ads.mediation.ironsource;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public class IronSourceAdapterUtils {
    static final String ADAPTER_VERSION_NAME = "310";
    static final String DEFAULT_INSTANCE_ID = "0";
    static final String KEY_APP_KEY = "appKey";
    static final String KEY_INSTANCE_ID = "instanceId";
    static final String MEDIATION_NAME = "AdMob";
    static final String TAG = IronSourceMediationAdapter.class.getSimpleName();
    private static Handler uiHandler;

    static synchronized void sendEventOnUIThread(Runnable runnable) {
        if (uiHandler == null) {
            uiHandler = new Handler(Looper.getMainLooper());
        }
        uiHandler.post(runnable);
    }
}
