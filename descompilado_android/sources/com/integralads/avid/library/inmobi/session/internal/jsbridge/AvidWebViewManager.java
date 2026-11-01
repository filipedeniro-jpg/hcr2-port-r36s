package com.integralads.avid.library.inmobi.session.internal.jsbridge;

import android.webkit.WebView;
import androidx.annotation.VisibleForTesting;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.inmobi.weakreference.AvidWebView;

/* JADX INFO: loaded from: classes2.dex */
public class AvidWebViewManager implements AvidJavascriptInterface.AvidJavascriptInterfaceCallback {
    private final InternalAvidAdSessionContext avidAdSessionContext;
    private final AvidBridgeManager avidBridgeManager;
    private final AvidWebView avidWebView = new AvidWebView(null);
    private AvidJavascriptInterface javascriptInterface;

    public AvidWebViewManager(InternalAvidAdSessionContext internalAvidAdSessionContext, AvidBridgeManager avidBridgeManager) {
        this.avidAdSessionContext = internalAvidAdSessionContext;
        this.avidBridgeManager = avidBridgeManager;
    }

    public void setWebView(WebView webView) {
        if (this.avidWebView.get() == webView) {
            return;
        }
        this.avidBridgeManager.setWebView(null);
        clearJavascriptInterface();
        this.avidWebView.set(webView);
        if (webView != null) {
            this.javascriptInterface = new AvidJavascriptInterface(this.avidAdSessionContext);
            this.javascriptInterface.setCallback(this);
            webView.addJavascriptInterface(this.javascriptInterface, "avid");
        }
    }

    public void destroy() {
        setWebView(null);
    }

    private void clearJavascriptInterface() {
        AvidJavascriptInterface avidJavascriptInterface = this.javascriptInterface;
        if (avidJavascriptInterface != null) {
            avidJavascriptInterface.setCallback(null);
            this.javascriptInterface = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.integralads.avid.library.inmobi.session.internal.jsbridge.AvidJavascriptInterface.AvidJavascriptInterfaceCallback
    public void onAvidAdSessionContextInvoked() {
        this.avidBridgeManager.setWebView((WebView) this.avidWebView.get());
    }

    @VisibleForTesting
    AvidJavascriptInterface getJavascriptInterface() {
        return this.javascriptInterface;
    }
}
