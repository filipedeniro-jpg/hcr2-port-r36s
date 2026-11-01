package com.facebook.ads.redexgen.X;

import android.text.TextUtils;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class BN {
    private boolean B = true;
    private final BJ C;

    public BN(BJ mWebView) {
        this.C = mWebView;
    }

    public final void A() {
        if (!this.B) {
            return;
        }
        if (this.C.canGoBack() || this.C.canGoForward()) {
            this.B = false;
        } else {
            this.C.D("void((function() {try {  if (!window.performance || !window.performance.timing || !document ||       !document.body || document.body.scrollHeight <= 0 ||       !document.body.children || document.body.children.length < 1) {    return;  }  var nvtiming__an_t = window.performance.timing;  if (nvtiming__an_t.responseEnd > 0) {    console.log('ANNavResponseEnd:'+nvtiming__an_t.responseEnd);  }  if (nvtiming__an_t.domContentLoadedEventStart > 0) {    console.log('ANNavDomContentLoaded:' + nvtiming__an_t.domContentLoadedEventStart);  }  if (nvtiming__an_t.loadEventEnd > 0) {    console.log('ANNavLoadEventEnd:' + nvtiming__an_t.loadEventEnd);  }} catch(err) {  console.log('an_navigation_timing_error:' + err.message);}})());");
        }
    }

    private static long B(String message, String prefix) {
        long jLongValue = -1;
        String strSubstring = message.substring(prefix.length());
        if (TextUtils.isEmpty(strSubstring)) {
            return -1L;
        }
        try {
            Long lValueOf = Long.valueOf(Long.parseLong(strSubstring));
            if (lValueOf.longValue() < 0) {
                return -1L;
            }
            jLongValue = lValueOf.longValue();
            return jLongValue;
        } catch (NumberFormatException e) {
            return jLongValue;
        }
    }

    public final void B(String message) {
        if (!this.B) {
            return;
        }
        if (message.startsWith("ANNavResponseEnd:")) {
            this.C.G(B(message, "ANNavResponseEnd:"));
        } else if (message.startsWith("ANNavDomContentLoaded:")) {
            this.C.E(B(message, "ANNavDomContentLoaded:"));
        } else {
            if (!message.startsWith("ANNavLoadEventEnd:")) {
                return;
            }
            this.C.F(B(message, "ANNavLoadEventEnd:"));
        }
    }

    public final void C(boolean mIsEnabled) {
        this.B = mIsEnabled;
    }
}
