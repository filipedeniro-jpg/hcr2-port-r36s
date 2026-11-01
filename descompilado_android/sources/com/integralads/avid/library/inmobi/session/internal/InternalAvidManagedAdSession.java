package com.integralads.avid.library.inmobi.session.internal;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import androidx.annotation.VisibleForTesting;
import com.integralads.avid.library.inmobi.session.ExternalAvidAdSessionContext;
import com.integralads.avid.library.inmobi.session.internal.trackingwebview.AvidJavaScriptResourceInjector;
import com.integralads.avid.library.inmobi.session.internal.trackingwebview.AvidTrackingWebViewManager;

/* JADX INFO: loaded from: classes2.dex */
public abstract class InternalAvidManagedAdSession extends InternalAvidAdSession<View> {
    private AvidTrackingWebViewManager trackingWebViewManager;
    private final WebView webView;

    public InternalAvidManagedAdSession(Context context, String str, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        super(context, str, externalAvidAdSessionContext);
        this.webView = new WebView(context.getApplicationContext());
        this.trackingWebViewManager = new AvidTrackingWebViewManager(this.webView);
    }

    @Override // com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession
    public WebView getWebView() {
        return this.webView;
    }

    public AvidJavaScriptResourceInjector getJavaScriptResourceInjector() {
        return this.trackingWebViewManager;
    }

    @Override // com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession
    public void onStart() {
        super.onStart();
        updateWebViewManager();
        this.trackingWebViewManager.loadHTML();
    }

    @VisibleForTesting
    void setTrackingWebViewManager(AvidTrackingWebViewManager avidTrackingWebViewManager) {
        this.trackingWebViewManager = avidTrackingWebViewManager;
    }
}
