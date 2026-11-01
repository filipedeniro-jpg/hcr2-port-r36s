package com.ironsource.sdk.ISNAdView;

import android.webkit.JavascriptInterface;

/* JADX INFO: loaded from: classes2.dex */
public class ISNAdViewWebViewJSInterface {
    private ISNAdView mIsnAdView;

    ISNAdViewWebViewJSInterface(ISNAdView iSNAdView) {
        this.mIsnAdView = iSNAdView;
    }

    @JavascriptInterface
    public void receiveMessageFromExternal(String str) {
        this.mIsnAdView.receiveMessageFromWebView(str);
    }
}
