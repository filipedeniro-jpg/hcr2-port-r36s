package com.integralads.avid.library.inmobi.session.internal.trackingwebview;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/* JADX INFO: loaded from: classes2.dex */
public class AvidWebViewClient extends WebViewClient {
    private AvidWebViewClientListener listener;

    public interface AvidWebViewClientListener {
        void webViewDidLoadData();
    }

    public AvidWebViewClientListener getListener() {
        return this.listener;
    }

    public void setListener(AvidWebViewClientListener avidWebViewClientListener) {
        this.listener = avidWebViewClientListener;
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        AvidWebViewClientListener avidWebViewClientListener = this.listener;
        if (avidWebViewClientListener != null) {
            avidWebViewClientListener.webViewDidLoadData();
        }
    }
}
