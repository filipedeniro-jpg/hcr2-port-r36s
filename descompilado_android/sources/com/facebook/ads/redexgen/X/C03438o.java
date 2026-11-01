package com.facebook.ads.redexgen.X;

import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.Locale;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8o, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C03438o extends WebViewClient {
    public final /* synthetic */ C03428n B;

    private C03438o(C03428n c03428n) {
        this.B = c03428n;
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView view, String url) {
        this.B.C();
        this.B.J = true;
        this.B.J(String.format(Locale.US, "setAuthKey('%s');", this.B.F));
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        this.B.C();
    }
}
