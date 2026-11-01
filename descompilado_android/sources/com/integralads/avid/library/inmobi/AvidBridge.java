package com.integralads.avid.library.inmobi;

import android.text.TextUtils;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class AvidBridge {
    public static final String APP_STATE_ACTIVE = "active";
    public static final String APP_STATE_INACTIVE = "inactive";
    private static String avidJS;

    public static void setAvidJs(@NonNull String str) {
        avidJS = str;
    }

    public static boolean isAvidJsReady() {
        return !TextUtils.isEmpty(avidJS);
    }

    public static String getAvidJs() {
        return avidJS;
    }

    public static void cleanUpAvidJS() {
        avidJS = null;
    }
}
