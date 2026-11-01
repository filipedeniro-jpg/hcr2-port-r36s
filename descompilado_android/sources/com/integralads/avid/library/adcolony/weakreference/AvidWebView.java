package com.integralads.avid.library.adcolony.weakreference;

import android.webkit.WebView;
import com.integralads.avid.library.adcolony.utils.AvidCommand;

/* JADX INFO: loaded from: classes2.dex */
public class AvidWebView extends AvidView<WebView> {
    public AvidWebView(WebView webView) {
        super(webView);
    }

    public void injectJavaScript(String str) {
        injectFormattedJavaScript(AvidCommand.formatJavaScript(str));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void injectFormattedJavaScript(String str) {
        WebView webView = (WebView) get();
        if (webView != null) {
            webView.loadUrl(str);
        }
    }
}
