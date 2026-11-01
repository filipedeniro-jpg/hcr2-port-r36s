package com.fingersoft.liveops_sdk.utils;

import com.fingersoft.liveops_sdk.LiveopsManager;

/* JADX INFO: loaded from: classes.dex */
public class CommonUtils {
    public static void log(String str) {
        if (str == null) {
            return;
        }
        Log.d(LiveopsManager.TAG, str);
    }
}
