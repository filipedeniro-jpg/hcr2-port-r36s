package com.unity3d.services.core.log;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceLogLevel {
    private static final String LOG_TAG = "UnityAds";
    private String _receivingMethodName;

    public String getLogTag() {
        return LOG_TAG;
    }

    public DeviceLogLevel(String str) {
        this._receivingMethodName = null;
        this._receivingMethodName = str;
    }

    public String getReceivingMethodName() {
        return this._receivingMethodName;
    }
}
