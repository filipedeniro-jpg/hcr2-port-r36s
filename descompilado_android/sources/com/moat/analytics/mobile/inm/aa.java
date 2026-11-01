package com.moat.analytics.mobile.inm;

import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class aa extends b implements WebAdTracker {
    aa(@Nullable ViewGroup viewGroup) {
        this(ab.a(viewGroup, false).c(null));
        if (viewGroup == null) {
            p.a("[ERROR] ", 3, "WebAdTracker", this, "WebAdTracker initialization not successful, Target ViewGroup is null");
            this.a = new m("Target ViewGroup is null");
        }
        if (this.b == null) {
            p.a("[ERROR] ", 3, "WebAdTracker", this, "WebAdTracker initialization not successful, No WebView to track inside of ad container");
            this.a = new m("No WebView to track inside of ad container");
        }
    }

    aa(@Nullable WebView webView) {
        super(webView, false, false);
        p.a(3, "WebAdTracker", this, "Initializing.");
        if (webView == null) {
            p.a("[ERROR] ", 3, "WebAdTracker", this, "WebAdTracker initialization not successful, WebView is null");
            this.a = new m("WebView is null");
            return;
        }
        try {
            super.a(webView);
            p.a("[SUCCESS] ", a() + " created for " + g());
        } catch (m e) {
            this.a = e;
        }
    }

    @Override // com.moat.analytics.mobile.inm.b
    String a() {
        return "WebAdTracker";
    }
}
