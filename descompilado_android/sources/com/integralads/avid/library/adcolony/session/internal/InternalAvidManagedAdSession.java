package com.integralads.avid.library.adcolony.session.internal;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import androidx.annotation.VisibleForTesting;
import com.integralads.avid.library.adcolony.session.ExternalAvidAdSessionContext;
import com.integralads.avid.library.adcolony.session.internal.trackingwebview.AvidJavaScriptResourceInjector;
import com.integralads.avid.library.adcolony.session.internal.trackingwebview.AvidTrackingWebViewManager;

/* JADX INFO: loaded from: classes2.dex */
public abstract class InternalAvidManagedAdSession extends InternalAvidAdSession<View> {
    private AvidTrackingWebViewManager a;
    private final WebView b;

    public InternalAvidManagedAdSession(Context context, String str, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        super(context, str, externalAvidAdSessionContext);
        this.b = new WebView(context.getApplicationContext());
        this.a = new AvidTrackingWebViewManager(this.b);
    }

    @Override // com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession
    public WebView getWebView() {
        return this.b;
    }

    public AvidJavaScriptResourceInjector getJavaScriptResourceInjector() {
        return this.a;
    }

    @Override // com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession
    public void onStart() {
        super.onStart();
        updateWebViewManager();
        this.a.loadHTML();
    }

    @VisibleForTesting
    void a(AvidTrackingWebViewManager avidTrackingWebViewManager) {
        this.a = avidTrackingWebViewManager;
    }
}
