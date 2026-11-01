package com.facebook.ads.internal.api;

import androidx.annotation.Keep;
import com.facebook.ads.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class BuildConfigApi {
    public static String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    public static boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
