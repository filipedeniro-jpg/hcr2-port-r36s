package com.moat.analytics.mobile.iro;

import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
final class x extends c implements WebAdTracker {
    @Override // com.moat.analytics.mobile.iro.c
    /* JADX INFO: renamed from: ˊ */
    final String mo469() {
        return "WebAdTracker";
    }

    x(@Nullable ViewGroup viewGroup) {
        this(v.m612(viewGroup, false).orElse(null));
        if (viewGroup == null) {
            String str = "WebAdTracker initialization not successful, Target ViewGroup is null";
            b.m461(3, "WebAdTracker", this, str);
            b.m459("[ERROR] ", str);
            this.f518 = new o("Target ViewGroup is null");
        }
        if (this.f522 == null) {
            String str2 = "WebAdTracker initialization not successful, No WebView to track inside of ad container";
            b.m461(3, "WebAdTracker", this, str2);
            b.m459("[ERROR] ", str2);
            this.f518 = new o("No WebView to track inside of ad container");
        }
    }

    x(@Nullable WebView webView) {
        super(webView, false, false);
        b.m461(3, "WebAdTracker", this, "Initializing.");
        if (webView == null) {
            String str = "WebAdTracker initialization not successful, WebView is null";
            b.m461(3, "WebAdTracker", this, str);
            b.m459("[ERROR] ", str);
            this.f518 = new o("WebView is null");
            return;
        }
        try {
            super.m471(webView);
            b.m459("[SUCCESS] ", "WebAdTracker created for " + m468());
        } catch (o e) {
            this.f518 = e;
        }
    }
}
