package com.moat.analytics.mobile.cha;

import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
final class v extends d implements WebAdTracker {
    @Override // com.moat.analytics.mobile.cha.d
    /* JADX INFO: renamed from: ˋ */
    final String mo303() {
        return "WebAdTracker";
    }

    v(@Nullable ViewGroup viewGroup) {
        this(x.m444(viewGroup, false).orElse(null));
        if (viewGroup == null) {
            String str = "WebAdTracker initialization not successful, Target ViewGroup is null";
            a.m273(3, "WebAdTracker", this, str);
            a.m270("[ERROR] ", str);
            this.f309 = new o("Target ViewGroup is null");
        }
        if (this.f307 == null) {
            String str2 = "WebAdTracker initialization not successful, No WebView to track inside of ad container";
            a.m273(3, "WebAdTracker", this, str2);
            a.m270("[ERROR] ", str2);
            this.f309 = new o("No WebView to track inside of ad container");
        }
    }

    v(@Nullable WebView webView) {
        super(webView, false, false);
        a.m273(3, "WebAdTracker", this, "Initializing.");
        if (webView == null) {
            String str = "WebAdTracker initialization not successful, WebView is null";
            a.m273(3, "WebAdTracker", this, str);
            a.m270("[ERROR] ", str);
            this.f309 = new o("WebView is null");
            return;
        }
        try {
            super.m306(webView);
            a.m270("[SUCCESS] ", "WebAdTracker created for " + m299());
        } catch (o e) {
            this.f309 = e;
        }
    }
}
