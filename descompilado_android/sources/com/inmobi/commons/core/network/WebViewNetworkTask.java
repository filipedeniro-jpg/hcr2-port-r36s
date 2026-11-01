package com.inmobi.commons.core.network;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* JADX INFO: loaded from: classes2.dex */
public class WebViewNetworkTask {
    private static final String d = "WebViewNetworkTask";
    public c a;
    public WebViewClient b;
    public NetworkTaskWebView c;

    public WebViewNetworkTask(c cVar, WebViewClient webViewClient) {
        this.b = webViewClient;
        this.a = cVar;
    }

    public class NetworkTaskWebView extends WebView {
        public boolean a;

        public NetworkTaskWebView(Context context) {
            super(context);
        }

        @Override // android.webkit.WebView
        public void destroy() {
            this.a = true;
            super.destroy();
        }
    }
}
