package com.integralads.avid.library.adcolony.session.internal.jsbridge;

import android.webkit.WebView;
import androidx.annotation.VisibleForTesting;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.adcolony.weakreference.AvidWebView;

/* JADX INFO: loaded from: classes2.dex */
public class AvidWebViewManager implements AvidJavascriptInterface.AvidJavascriptInterfaceCallback {
    private final InternalAvidAdSessionContext a;
    private final AvidWebView b = new AvidWebView(null);
    private final AvidBridgeManager c;
    private AvidJavascriptInterface d;

    public AvidWebViewManager(InternalAvidAdSessionContext internalAvidAdSessionContext, AvidBridgeManager avidBridgeManager) {
        this.a = internalAvidAdSessionContext;
        this.c = avidBridgeManager;
    }

    public void setWebView(WebView webView) {
        if (this.b.get() == webView) {
            return;
        }
        this.c.setWebView(null);
        b();
        this.b.set(webView);
        if (webView != null) {
            this.d = new AvidJavascriptInterface(this.a);
            this.d.setCallback(this);
            webView.addJavascriptInterface(this.d, "avid");
        }
    }

    public void destroy() {
        setWebView(null);
    }

    private void b() {
        AvidJavascriptInterface avidJavascriptInterface = this.d;
        if (avidJavascriptInterface != null) {
            avidJavascriptInterface.setCallback(null);
            this.d = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.integralads.avid.library.adcolony.session.internal.jsbridge.AvidJavascriptInterface.AvidJavascriptInterfaceCallback
    public void onAvidAdSessionContextInvoked() {
        this.c.setWebView((WebView) this.b.get());
    }

    @VisibleForTesting
    AvidJavascriptInterface a() {
        return this.d;
    }
}
