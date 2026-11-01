package com.chartboost.sdk.impl;

import android.app.Application;
import android.webkit.WebView;
import com.chartboost.sdk.Libraries.CBLogging;
import com.moat.analytics.mobile.cha.MoatAnalytics;
import com.moat.analytics.mobile.cha.MoatFactory;
import com.moat.analytics.mobile.cha.MoatOptions;
import com.moat.analytics.mobile.cha.WebAdTracker;

/* JADX INFO: loaded from: classes.dex */
public class r implements q {
    private static String b = "r";
    WebAdTracker a = null;

    @Override // com.chartboost.sdk.impl.q
    public void a(Application application, boolean z, boolean z2, boolean z3) {
        String str = b;
        StringBuilder sb = new StringBuilder();
        sb.append("start MOAT provider, Debugging Enabled: ");
        sb.append(z);
        sb.append("Location Enabled:");
        sb.append(!z2);
        sb.append("idfaCollectionEnabled:");
        sb.append(!z3);
        CBLogging.a(str, sb.toString());
        MoatOptions moatOptions = new MoatOptions();
        moatOptions.disableLocationServices = z2;
        moatOptions.disableAdIdCollection = z3;
        moatOptions.loggingEnabled = z;
        MoatAnalytics.getInstance().start(moatOptions, application);
    }

    @Override // com.chartboost.sdk.impl.p
    public void a(WebView webView) {
        this.a = MoatFactory.create().createWebAdTracker(webView);
    }

    @Override // com.chartboost.sdk.impl.p
    public void a() {
        if (this.a != null) {
            CBLogging.a(b, "start MOAT tracker");
            this.a.startTracking();
        }
    }

    @Override // com.chartboost.sdk.impl.p
    public void b() {
        if (this.a != null) {
            CBLogging.a(b, "stop MOAT tracker");
            this.a.stopTracking();
            this.a = null;
        }
    }
}
