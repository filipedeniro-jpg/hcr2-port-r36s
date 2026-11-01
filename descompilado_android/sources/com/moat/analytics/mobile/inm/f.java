package com.moat.analytics.mobile.inm;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdActivity;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
class f {

    @Nullable
    private static WebAdTracker a;
    private static WeakReference<Activity> b = new WeakReference<>(null);

    f() {
    }

    private static void a() {
        if (a != null) {
            p.a(3, "GMAInterstitialHelper", b.get(), "Stopping to track GMA interstitial");
            a.stopTracking();
            a = null;
        }
    }

    static void a(Activity activity) {
        try {
            if (w.a().a == w.d.OFF) {
                return;
            }
            if (!b(activity)) {
                a();
                b = new WeakReference<>(null);
                return;
            }
            if (b.get() == null || b.get() != activity) {
                View decorView = activity.getWindow().getDecorView();
                if (decorView instanceof ViewGroup) {
                    com.moat.analytics.mobile.inm.a.b.a<WebView> aVarA = ab.a((ViewGroup) decorView, true);
                    if (!aVarA.c()) {
                        p.a(3, "GMAInterstitialHelper", activity, "Sorry, no WebView in this activity");
                    } else {
                        b = new WeakReference<>(activity);
                        a(aVarA.b());
                    }
                }
            }
        } catch (Exception e) {
            m.a(e);
        }
    }

    private static void a(WebView webView) {
        p.a(3, "GMAInterstitialHelper", b.get(), "Starting to track GMA interstitial");
        WebAdTracker webAdTrackerCreateWebAdTracker = MoatFactory.create().createWebAdTracker(webView);
        a = webAdTrackerCreateWebAdTracker;
        webAdTrackerCreateWebAdTracker.startTracking();
    }

    private static boolean b(Activity activity) {
        String name = activity.getClass().getName();
        p.a(3, "GMAInterstitialHelper", activity, "Activity name: " + name);
        return name.contains(AdActivity.CLASS_NAME);
    }
}
