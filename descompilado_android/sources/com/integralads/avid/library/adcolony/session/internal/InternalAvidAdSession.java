package com.integralads.avid.library.adcolony.session.internal;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import androidx.annotation.VisibleForTesting;
import com.integralads.avid.library.adcolony.deferred.AvidDeferredAdSessionListener;
import com.integralads.avid.library.adcolony.deferred.AvidDeferredAdSessionListenerImpl;
import com.integralads.avid.library.adcolony.session.ExternalAvidAdSessionContext;
import com.integralads.avid.library.adcolony.session.internal.jsbridge.AvidBridgeManager;
import com.integralads.avid.library.adcolony.session.internal.jsbridge.AvidWebViewManager;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;
import com.integralads.avid.library.adcolony.utils.AvidTimestamp;
import com.integralads.avid.library.adcolony.weakreference.AvidView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class InternalAvidAdSession<T extends View> implements AvidBridgeManager.AvidBridgeManagerListener {
    private final InternalAvidAdSessionContext a;
    private AvidBridgeManager b;
    private AvidWebViewManager c;
    private AvidView<T> d;
    private AvidDeferredAdSessionListenerImpl e;
    private InternalAvidAdSessionListener f;
    private boolean g;
    private boolean h;
    private final ObstructionsWhiteList i;
    private a j;
    private double k;

    enum a {
        AD_STATE_IDLE,
        AD_STATE_VISIBLE,
        AD_STATE_HIDDEN
    }

    public abstract MediaType getMediaType();

    public abstract SessionType getSessionType();

    public abstract WebView getWebView();

    public void onStart() {
    }

    protected void onViewRegistered() {
    }

    protected void onViewUnregistered() {
    }

    public InternalAvidAdSession(Context context, String str, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        this.a = new InternalAvidAdSessionContext(context, str, getSessionType().toString(), getMediaType().toString(), externalAvidAdSessionContext);
        this.b = new AvidBridgeManager(this.a);
        this.b.setListener(this);
        this.c = new AvidWebViewManager(this.a, this.b);
        this.d = new AvidView<>(null);
        this.g = !externalAvidAdSessionContext.isDeferred();
        if (!this.g) {
            this.e = new AvidDeferredAdSessionListenerImpl(this, this.b);
        }
        this.i = new ObstructionsWhiteList();
        c();
    }

    public String getAvidAdSessionId() {
        return this.a.getAvidAdSessionId();
    }

    public ExternalAvidAdSessionContext getAvidAdSessionContext() {
        return this.a.getAvidAdSessionContext();
    }

    public T getView() {
        return this.d.get();
    }

    public AvidDeferredAdSessionListener getAvidDeferredAdSessionListener() {
        return this.e;
    }

    public InternalAvidAdSessionListener getListener() {
        return this.f;
    }

    public void setListener(InternalAvidAdSessionListener internalAvidAdSessionListener) {
        this.f = internalAvidAdSessionListener;
    }

    public boolean isEmpty() {
        return this.d.isEmpty();
    }

    public boolean isActive() {
        return this.h;
    }

    public boolean isReady() {
        return this.g;
    }

    public AvidBridgeManager getAvidBridgeManager() {
        return this.b;
    }

    public ObstructionsWhiteList getObstructionsWhiteList() {
        return this.i;
    }

    public void registerAdView(T t) {
        if (doesManageView(t)) {
            return;
        }
        c();
        this.d.set(t);
        onViewRegistered();
        sessionStateCanBeChanged();
    }

    public void unregisterAdView(T t) {
        if (doesManageView(t)) {
            c();
            cleanupViewState();
            this.d.set(null);
            onViewUnregistered();
            sessionStateCanBeChanged();
        }
    }

    public boolean doesManageView(View view) {
        return this.d.contains(view);
    }

    public void onEnd() {
        cleanupViewState();
        AvidDeferredAdSessionListenerImpl avidDeferredAdSessionListenerImpl = this.e;
        if (avidDeferredAdSessionListenerImpl != null) {
            avidDeferredAdSessionListenerImpl.destroy();
        }
        this.b.destroy();
        this.c.destroy();
        this.g = false;
        sessionStateCanBeChanged();
        InternalAvidAdSessionListener internalAvidAdSessionListener = this.f;
        if (internalAvidAdSessionListener != null) {
            internalAvidAdSessionListener.sessionDidEnd(this);
        }
    }

    public void onReady() {
        this.g = true;
        sessionStateCanBeChanged();
    }

    @Override // com.integralads.avid.library.adcolony.session.internal.jsbridge.AvidBridgeManager.AvidBridgeManagerListener
    public void avidBridgeManagerDidInjectAvidJs() {
        sessionStateCanBeChanged();
    }

    public void setScreenMode(boolean z) {
        if (isActive()) {
            this.b.publishAppState(z ? "active" : "inactive");
        }
    }

    public void publishNativeViewStateCommand(String str, double d) {
        if (d > this.k) {
            this.b.callAvidbridge(str);
            this.j = a.AD_STATE_VISIBLE;
        }
    }

    public void publishEmptyNativeViewStateCommand(String str, double d) {
        if (d <= this.k || this.j == a.AD_STATE_HIDDEN) {
            return;
        }
        this.b.callAvidbridge(str);
        this.j = a.AD_STATE_HIDDEN;
    }

    protected void cleanupViewState() {
        if (isActive()) {
            this.b.publishNativeViewState(AvidJSONUtil.getEmptyTreeJSONObject().toString());
        }
    }

    protected void updateWebViewManager() {
        this.c.setWebView(getWebView());
    }

    protected void sessionStateCanBeChanged() {
        boolean z = this.b.isActive() && this.g && !isEmpty();
        if (this.h != z) {
            setActive(z);
        }
    }

    protected void setActive(boolean z) {
        this.h = z;
        InternalAvidAdSessionListener internalAvidAdSessionListener = this.f;
        if (internalAvidAdSessionListener != null) {
            if (z) {
                internalAvidAdSessionListener.sessionHasBecomeActive(this);
            } else {
                internalAvidAdSessionListener.sessionHasResignedActive(this);
            }
        }
    }

    private void c() {
        this.k = AvidTimestamp.getCurrentTime();
        this.j = a.AD_STATE_IDLE;
    }

    @VisibleForTesting
    void a(AvidBridgeManager avidBridgeManager) {
        this.b = avidBridgeManager;
    }

    @VisibleForTesting
    a a() {
        return this.j;
    }

    @VisibleForTesting
    double b() {
        return this.k;
    }

    @VisibleForTesting
    void a(AvidWebViewManager avidWebViewManager) {
        this.c = avidWebViewManager;
    }
}
