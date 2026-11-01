package com.applovin.impl.adview;

import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class s extends WebViewClient {
    private final com.applovin.impl.sdk.n a;
    private WeakReference<a> b;

    public interface a {
        void a(r rVar);

        void b(r rVar);

        void c(r rVar);
    }

    public s(com.applovin.impl.sdk.h hVar) {
        this.a = hVar.w();
    }

    private void a(WebView webView, String str) {
        this.a.b("WebViewButtonClient", "Processing click on ad URL \"" + str + "\"");
        if (str == null || !(webView instanceof r)) {
            return;
        }
        r rVar = (r) webView;
        Uri uri = Uri.parse(str);
        String scheme = uri.getScheme();
        String host = uri.getHost();
        String path = uri.getPath();
        a aVar = this.b.get();
        if ("applovin".equalsIgnoreCase(scheme) && "com.applovin.sdk".equalsIgnoreCase(host) && aVar != null) {
            if ("/track_click".equals(path)) {
                aVar.a(rVar);
                return;
            }
            if ("/close_ad".equals(path)) {
                aVar.b(rVar);
                return;
            }
            if ("/skip_ad".equals(path)) {
                aVar.c(rVar);
                return;
            }
            this.a.c("WebViewButtonClient", "Unknown URL: " + str);
            this.a.c("WebViewButtonClient", "Path: " + path);
        }
    }

    public void a(WeakReference<a> weakReference) {
        this.b = weakReference;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        a(webView, str);
        return true;
    }
}
