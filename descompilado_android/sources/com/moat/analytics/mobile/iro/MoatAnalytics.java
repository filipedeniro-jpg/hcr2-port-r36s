package com.moat.analytics.mobile.iro;

import android.app.Application;
import androidx.annotation.UiThread;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MoatAnalytics {

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static MoatAnalytics f493;

    @UiThread
    public abstract void prepareNativeDisplayTracking(String str);

    public abstract void start(Application application);

    public abstract void start(MoatOptions moatOptions, Application application);

    public static synchronized MoatAnalytics getInstance() {
        if (f493 == null) {
            try {
                f493 = new j();
            } catch (Exception e) {
                o.m569(e);
                f493 = new NoOp.MoatAnalytics();
            }
        }
        return f493;
    }
}
