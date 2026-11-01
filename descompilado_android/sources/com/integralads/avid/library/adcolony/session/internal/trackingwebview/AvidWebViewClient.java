package com.integralads.avid.library.adcolony.session.internal.trackingwebview;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/* JADX INFO: loaded from: classes2.dex */
public class AvidWebViewClient extends WebViewClient {
    private AvidWebViewClientListener a;

    public interface AvidWebViewClientListener {
        void webViewDidLoadData();
    }

    public AvidWebViewClientListener getListener() {
        return this.a;
    }

    public void setListener(AvidWebViewClientListener avidWebViewClientListener) {
        this.a = avidWebViewClientListener;
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        AvidWebViewClientListener avidWebViewClientListener = this.a;
        if (avidWebViewClientListener != null) {
            avidWebViewClientListener.webViewDidLoadData();
        }
    }
}
