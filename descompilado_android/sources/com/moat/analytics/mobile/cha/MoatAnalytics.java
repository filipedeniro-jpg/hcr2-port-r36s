package com.moat.analytics.mobile.cha;

import android.app.Application;
import androidx.annotation.UiThread;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MoatAnalytics {

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private static MoatAnalytics f268;

    @UiThread
    public abstract void prepareNativeDisplayTracking(String str);

    public abstract void start(Application application);

    public abstract void start(MoatOptions moatOptions, Application application);

    public static synchronized MoatAnalytics getInstance() {
        if (f268 == null) {
            try {
                f268 = new f();
            } catch (Exception e) {
                o.m389(e);
                f268 = new NoOp.MoatAnalytics();
            }
        }
        return f268;
    }
}
