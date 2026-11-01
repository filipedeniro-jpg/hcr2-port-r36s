package com.integralads.avid.library.inmobi.session.internal;

import android.content.Context;
import android.webkit.WebView;
import com.integralads.avid.library.inmobi.session.ExternalAvidAdSessionContext;

/* JADX INFO: loaded from: classes2.dex */
public abstract class InternalAvidHtmlAdSession extends InternalAvidAdSession<WebView> {
    public InternalAvidHtmlAdSession(Context context, String str, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        super(context, str, externalAvidAdSessionContext);
    }

    @Override // com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession
    public WebView getWebView() {
        return getView();
    }

    @Override // com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession
    protected void onViewRegistered() {
        super.onViewRegistered();
        updateWebViewManager();
    }

    @Override // com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession
    protected void onViewUnregistered() {
        super.onViewUnregistered();
        updateWebViewManager();
    }
}
