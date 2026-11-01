package com.amazon.device.ads;

import android.annotation.SuppressLint;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
class ViewUtils {
    private final AndroidBuildInfo androidBuildInfo;

    public ViewUtils() {
        this(new AndroidBuildInfo());
    }

    ViewUtils(AndroidBuildInfo androidBuildInfo) {
        this.androidBuildInfo = androidBuildInfo;
    }

    @SuppressLint({"NewApi"})
    public boolean removeOnGlobalLayoutListener(ViewTreeObserver viewTreeObserver, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (!viewTreeObserver.isAlive()) {
            return false;
        }
        if (AndroidTargetUtils.isAtLeastAndroidAPI(this.androidBuildInfo, 16)) {
            viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener);
            return true;
        }
        viewTreeObserver.removeGlobalOnLayoutListener(onGlobalLayoutListener);
        return true;
    }
}
