package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.Build;
import android.webkit.WebView;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Im, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class CallableC0600Im implements Callable<String> {
    public final /* synthetic */ Context B;

    public CallableC0600Im(Context context) {
        this.B = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final String call() {
        String str = (String) C0601In.C.get();
        if (str != null) {
            return str;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                return C0601In.G(this.B);
            } catch (Throwable th) {
                C0601In.J(this.B, th);
            }
        }
        WebView webView = new WebView(this.B.getApplicationContext());
        String userAgentString = webView.getSettings().getUserAgentString();
        webView.destroy();
        if (userAgentString == null) {
            return userAgentString;
        }
        C0601In.C.set(userAgentString);
        return userAgentString;
    }
}
