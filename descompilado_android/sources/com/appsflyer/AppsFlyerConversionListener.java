package com.appsflyer;

import androidx.annotation.WorkerThread;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface AppsFlyerConversionListener {
    @WorkerThread
    void onAppOpenAttribution(Map<String, String> map);

    void onAttributionFailure(String str);

    @WorkerThread
    void onInstallConversionDataLoaded(Map<String, String> map);

    void onInstallConversionFailure(String str);
}
