package com.integralads.avid.library.adcolony;

import android.text.TextUtils;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class AvidBridge {
    public static final String APP_STATE_ACTIVE = "active";
    public static final String APP_STATE_INACTIVE = "inactive";
    private static String a;

    public static void setAvidJs(@NonNull String str) {
        a = str;
    }

    public static boolean isAvidJsReady() {
        return !TextUtils.isEmpty(a);
    }

    public static String getAvidJs() {
        return a;
    }

    public static void cleanUpAvidJS() {
        a = null;
    }
}
