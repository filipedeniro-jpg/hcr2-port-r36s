package com.moat.analytics.mobile.inm;

import android.app.Application;
import androidx.annotation.UiThread;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MoatAnalytics {
    private static MoatAnalytics a;

    public static synchronized MoatAnalytics getInstance() {
        if (a == null) {
            try {
                a = new k();
            } catch (Exception e) {
                m.a(e);
                a = new v.a();
            }
        }
        return a;
    }

    @UiThread
    public abstract void prepareNativeDisplayTracking(String str);

    public abstract void start(Application application);

    public abstract void start(MoatOptions moatOptions, Application application);
}
