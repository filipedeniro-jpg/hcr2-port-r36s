package com.integralads.avid.library.adcolony.utils;

import android.os.Build;
import android.view.View;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* JADX INFO: loaded from: classes2.dex */
public class AvidViewUtil {
    public static boolean isViewVisible(View view) {
        if (view.getVisibility() != 0) {
            return false;
        }
        return Build.VERSION.SDK_INT < 11 || ((double) view.getAlpha()) > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }
}
