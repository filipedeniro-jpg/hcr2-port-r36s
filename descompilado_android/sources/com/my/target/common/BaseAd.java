package com.my.target.common;

import androidx.annotation.NonNull;
import com.my.target.b;
import com.my.target.g;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseAd {

    @NonNull
    protected final b adConfig;

    public static void setDebugMode(boolean z) {
        g.enabled = z;
        if (z) {
            g.a("Debug mode enabled");
        }
    }

    public void setTrackingEnvironmentEnabled(boolean z) {
        this.adConfig.setTrackingEnvironmentEnabled(z);
    }

    public boolean isTrackingEnvironmentEnabled() {
        return this.adConfig.isTrackingEnvironmentEnabled();
    }

    public void setTrackingLocationEnabled(boolean z) {
        this.adConfig.setTrackingLocationEnabled(z);
    }

    public boolean isTrackingLocationEnabled() {
        return this.adConfig.isTrackingLocationEnabled();
    }

    @NonNull
    public CustomParams getCustomParams() {
        return this.adConfig.getCustomParams();
    }

    protected BaseAd(int i, @NonNull String str) {
        this.adConfig = b.newConfig(i, str);
    }
}
