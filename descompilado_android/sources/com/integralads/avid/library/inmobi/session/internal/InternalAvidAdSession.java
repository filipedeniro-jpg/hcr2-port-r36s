package com.integralads.avid.library.inmobi.session.internal;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import androidx.annotation.VisibleForTesting;
import com.integralads.avid.library.inmobi.deferred.AvidDeferredAdSessionListener;
import com.integralads.avid.library.inmobi.deferred.AvidDeferredAdSessionListenerImpl;
import com.integralads.avid.library.inmobi.session.ExternalAvidAdSessionContext;
import com.integralads.avid.library.inmobi.session.internal.jsbridge.AvidBridgeManager;
import com.integralads.avid.library.inmobi.session.internal.jsbridge.AvidWebViewManager;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.integralads.avid.library.inmobi.utils.AvidTimestamp;
import com.integralads.avid.library.inmobi.weakreference.AvidView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class InternalAvidAdSession<T extends View> implements AvidBridgeManager.AvidBridgeManagerListener {
    private AdState adState;
    private AvidBridgeManager avidBridgeManager;
    private AvidDeferredAdSessionListenerImpl avidDeferredAdSessionListener;
    private AvidView<T> avidView;
    private final InternalAvidAdSessionContext internalContext;
    private boolean isActive;
    private boolean isReady;
    private double lastUpdated;
    private InternalAvidAdSessionListener listener;
    private final ObstructionsWhiteList obstructionsWhiteList;
    private AvidWebViewManager webViewManager;

    enum AdState {
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
        this.internalContext = new InternalAvidAdSessionContext(context, str, getSessionType().toString(), getMediaType().toString(), externalAvidAdSessionContext);
        this.avidBridgeManager = new AvidBridgeManager(this.internalContext);
        this.avidBridgeManager.setListener(this);
        this.webViewManager = new AvidWebViewManager(this.internalContext, this.avidBridgeManager);
        this.avidView = new AvidView<>(null);
        this.isReady = !externalAvidAdSessionContext.isDeferred();
        if (!this.isReady) {
            this.avidDeferredAdSessionListener = new AvidDeferredAdSessionListenerImpl(this, this.avidBridgeManager);
        }
        this.obstructionsWhiteList = new ObstructionsWhiteList();
        onViewChanged();
    }

    public String getAvidAdSessionId() {
        return this.internalContext.getAvidAdSessionId();
    }

    public ExternalAvidAdSessionContext getAvidAdSessionContext() {
        return this.internalContext.getAvidAdSessionContext();
    }

    public T getView() {
        return this.avidView.get();
    }

    public AvidDeferredAdSessionListener getAvidDeferredAdSessionListener() {
        return this.avidDeferredAdSessionListener;
    }

    public InternalAvidAdSessionListener getListener() {
        return this.listener;
    }

    public void setListener(InternalAvidAdSessionListener internalAvidAdSessionListener) {
        this.listener = internalAvidAdSessionListener;
    }

    public boolean isEmpty() {
        return this.avidView.isEmpty();
    }

    public boolean isActive() {
        return this.isActive;
    }

    public boolean isReady() {
        return this.isReady;
    }

    public AvidBridgeManager getAvidBridgeManager() {
        return this.avidBridgeManager;
    }

    public ObstructionsWhiteList getObstructionsWhiteList() {
        return this.obstructionsWhiteList;
    }

    public void registerAdView(T t) {
        if (doesManageView(t)) {
            return;
        }
        onViewChanged();
        this.avidView.set(t);
        onViewRegistered();
        sessionStateCanBeChanged();
    }

    public void unregisterAdView(T t) {
        if (doesManageView(t)) {
            onViewChanged();
            cleanupViewState();
            this.avidView.set(null);
            onViewUnregistered();
            sessionStateCanBeChanged();
        }
    }

    public boolean doesManageView(View view) {
        return this.avidView.contains(view);
    }

    public void onEnd() {
        cleanupViewState();
        AvidDeferredAdSessionListenerImpl avidDeferredAdSessionListenerImpl = this.avidDeferredAdSessionListener;
        if (avidDeferredAdSessionListenerImpl != null) {
            avidDeferredAdSessionListenerImpl.destroy();
        }
        this.avidBridgeManager.destroy();
        this.webViewManager.destroy();
        this.isReady = false;
        sessionStateCanBeChanged();
        InternalAvidAdSessionListener internalAvidAdSessionListener = this.listener;
        if (internalAvidAdSessionListener != null) {
            internalAvidAdSessionListener.sessionDidEnd(this);
        }
    }

    public void onReady() {
        this.isReady = true;
        sessionStateCanBeChanged();
    }

    @Override // com.integralads.avid.library.inmobi.session.internal.jsbridge.AvidBridgeManager.AvidBridgeManagerListener
    public void avidBridgeManagerDidInjectAvidJs() {
        sessionStateCanBeChanged();
    }

    public void setScreenMode(boolean z) {
        if (isActive()) {
            this.avidBridgeManager.publishAppState(z ? "active" : "inactive");
        }
    }

    public void publishNativeViewStateCommand(String str, double d) {
        if (d > this.lastUpdated) {
            this.avidBridgeManager.callAvidbridge(str);
            this.adState = AdState.AD_STATE_VISIBLE;
        }
    }

    public void publishEmptyNativeViewStateCommand(String str, double d) {
        if (d <= this.lastUpdated || this.adState == AdState.AD_STATE_HIDDEN) {
            return;
        }
        this.avidBridgeManager.callAvidbridge(str);
        this.adState = AdState.AD_STATE_HIDDEN;
    }

    protected void cleanupViewState() {
        if (isActive()) {
            this.avidBridgeManager.publishNativeViewState(AvidJSONUtil.getEmptyTreeJSONObject().toString());
        }
    }

    protected void updateWebViewManager() {
        this.webViewManager.setWebView(getWebView());
    }

    protected void sessionStateCanBeChanged() {
        boolean z = this.avidBridgeManager.isActive() && this.isReady && !isEmpty();
        if (this.isActive != z) {
            setActive(z);
        }
    }

    protected void setActive(boolean z) {
        this.isActive = z;
        InternalAvidAdSessionListener internalAvidAdSessionListener = this.listener;
        if (internalAvidAdSessionListener != null) {
            if (z) {
                internalAvidAdSessionListener.sessionHasBecomeActive(this);
            } else {
                internalAvidAdSessionListener.sessionHasResignedActive(this);
            }
        }
    }

    private void onViewChanged() {
        this.lastUpdated = AvidTimestamp.getCurrentTime();
        this.adState = AdState.AD_STATE_IDLE;
    }

    @VisibleForTesting
    void setAvidBridgeManager(AvidBridgeManager avidBridgeManager) {
        this.avidBridgeManager = avidBridgeManager;
    }

    @VisibleForTesting
    AdState getAdState() {
        return this.adState;
    }

    @VisibleForTesting
    double getLastUpdated() {
        return this.lastUpdated;
    }

    @VisibleForTesting
    void setAvidWebViewManager(AvidWebViewManager avidWebViewManager) {
        this.webViewManager = avidWebViewManager;
    }
}
