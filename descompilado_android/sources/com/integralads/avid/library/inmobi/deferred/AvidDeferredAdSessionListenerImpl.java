package com.integralads.avid.library.inmobi.deferred;

import com.integralads.avid.library.inmobi.base.AvidBaseListenerImpl;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.inmobi.session.internal.jsbridge.AvidBridgeManager;

/* JADX INFO: loaded from: classes2.dex */
public class AvidDeferredAdSessionListenerImpl extends AvidBaseListenerImpl implements AvidDeferredAdSessionListener {
    public AvidDeferredAdSessionListenerImpl(InternalAvidAdSession internalAvidAdSession, AvidBridgeManager avidBridgeManager) {
        super(internalAvidAdSession, avidBridgeManager);
    }

    @Override // com.integralads.avid.library.inmobi.deferred.AvidDeferredAdSessionListener
    public void recordReadyEvent() {
        assertSessionIsNotEnded();
        if (getAvidAdSession().isReady()) {
            throw new IllegalStateException("The AVID ad session is already ready. Please ensure you are only calling recordReadyEvent once for the deferred AVID ad session.");
        }
        getAvidBridgeManager().publishReadyEventForDeferredAdSession();
        getAvidAdSession().onReady();
    }
}
