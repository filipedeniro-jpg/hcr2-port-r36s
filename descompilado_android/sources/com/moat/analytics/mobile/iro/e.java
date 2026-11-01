package com.moat.analytics.mobile.iro;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdActivity;
import com.moat.analytics.mobile.iro.base.functional.Optional;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
final class e {

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static WeakReference<Activity> f536 = new WeakReference<>(null);

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    @Nullable
    private static WebAdTracker f537;

    e() {
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    static void m487(Activity activity) {
        try {
            if (t.m598().f660 == t.c.f676) {
                return;
            }
            String name = activity.getClass().getName();
            b.m461(3, "GMAInterstitialHelper", activity, "Activity name: " + name);
            if (name.contains(AdActivity.CLASS_NAME)) {
                if (f536.get() == null || f536.get() != activity) {
                    View decorView = activity.getWindow().getDecorView();
                    if (decorView instanceof ViewGroup) {
                        Optional<WebView> optionalM612 = v.m612((ViewGroup) decorView, true);
                        if (optionalM612.isPresent()) {
                            f536 = new WeakReference<>(activity);
                            WebView webView = optionalM612.get();
                            b.m461(3, "GMAInterstitialHelper", f536.get(), "Starting to track GMA interstitial");
                            WebAdTracker webAdTrackerCreateWebAdTracker = MoatFactory.create().createWebAdTracker(webView);
                            f537 = webAdTrackerCreateWebAdTracker;
                            webAdTrackerCreateWebAdTracker.startTracking();
                            return;
                        }
                        b.m461(3, "GMAInterstitialHelper", activity, "Sorry, no WebView in this activity");
                        return;
                    }
                    return;
                }
                return;
            }
            if (f537 != null) {
                b.m461(3, "GMAInterstitialHelper", f536.get(), "Stopping to track GMA interstitial");
                f537.stopTracking();
                f537 = null;
            }
            f536 = new WeakReference<>(null);
        } catch (Exception e) {
            o.m569(e);
        }
    }
}
