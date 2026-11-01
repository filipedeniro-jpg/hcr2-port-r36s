package com.moat.analytics.mobile.cha;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdActivity;
import com.moat.analytics.mobile.cha.base.functional.Optional;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
final class e {

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static WeakReference<Activity> f311 = new WeakReference<>(null);

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    @Nullable
    private static WebAdTracker f312;

    e() {
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    static void m308(Activity activity) {
        try {
            if (t.m423().f441 == t.a.f453) {
                return;
            }
            String name = activity.getClass().getName();
            a.m273(3, "GMAInterstitialHelper", activity, "Activity name: " + name);
            if (name.contains(AdActivity.CLASS_NAME)) {
                if (f311.get() == null || f311.get() != activity) {
                    View decorView = activity.getWindow().getDecorView();
                    if (decorView instanceof ViewGroup) {
                        Optional<WebView> optionalM444 = x.m444((ViewGroup) decorView, true);
                        if (optionalM444.isPresent()) {
                            f311 = new WeakReference<>(activity);
                            WebView webView = optionalM444.get();
                            a.m273(3, "GMAInterstitialHelper", f311.get(), "Starting to track GMA interstitial");
                            WebAdTracker webAdTrackerCreateWebAdTracker = MoatFactory.create().createWebAdTracker(webView);
                            f312 = webAdTrackerCreateWebAdTracker;
                            webAdTrackerCreateWebAdTracker.startTracking();
                            return;
                        }
                        a.m273(3, "GMAInterstitialHelper", activity, "Sorry, no WebView in this activity");
                        return;
                    }
                    return;
                }
                return;
            }
            if (f312 != null) {
                a.m273(3, "GMAInterstitialHelper", f311.get(), "Stopping to track GMA interstitial");
                f312.stopTracking();
                f312 = null;
            }
            f311 = new WeakReference<>(null);
        } catch (Exception e) {
            o.m389(e);
        }
    }
}
