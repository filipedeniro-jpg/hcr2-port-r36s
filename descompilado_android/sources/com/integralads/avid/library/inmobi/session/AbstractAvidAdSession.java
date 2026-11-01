package com.integralads.avid.library.inmobi.session;

import android.app.Activity;
import android.view.View;
import com.integralads.avid.library.inmobi.AvidManager;
import com.integralads.avid.library.inmobi.deferred.AvidDeferredAdSessionListener;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractAvidAdSession<T extends View> {
    private String avidAdSessionId = UUID.randomUUID().toString();

    public String getAvidAdSessionId() {
        return this.avidAdSessionId;
    }

    public void registerAdView(T t, Activity activity) {
        InternalAvidAdSession internalAvidAdSessionFindInternalAvidAdSessionById = AvidManager.getInstance().findInternalAvidAdSessionById(this.avidAdSessionId);
        if (internalAvidAdSessionFindInternalAvidAdSessionById != null) {
            internalAvidAdSessionFindInternalAvidAdSessionById.registerAdView(t);
        }
        AvidManager.getInstance().registerActivity(activity);
    }

    public void unregisterAdView(T t) {
        InternalAvidAdSession internalAvidAdSessionFindInternalAvidAdSessionById = AvidManager.getInstance().findInternalAvidAdSessionById(this.avidAdSessionId);
        if (internalAvidAdSessionFindInternalAvidAdSessionById != null) {
            internalAvidAdSessionFindInternalAvidAdSessionById.unregisterAdView(t);
        }
    }

    public void endSession() {
        InternalAvidAdSession internalAvidAdSessionFindInternalAvidAdSessionById = AvidManager.getInstance().findInternalAvidAdSessionById(getAvidAdSessionId());
        if (internalAvidAdSessionFindInternalAvidAdSessionById != null) {
            internalAvidAdSessionFindInternalAvidAdSessionById.onEnd();
        }
    }

    public AvidDeferredAdSessionListener getAvidDeferredAdSessionListener() {
        InternalAvidAdSession internalAvidAdSessionFindInternalAvidAdSessionById = AvidManager.getInstance().findInternalAvidAdSessionById(getAvidAdSessionId());
        AvidDeferredAdSessionListener avidDeferredAdSessionListener = internalAvidAdSessionFindInternalAvidAdSessionById != null ? internalAvidAdSessionFindInternalAvidAdSessionById.getAvidDeferredAdSessionListener() : null;
        if (avidDeferredAdSessionListener != null) {
            return avidDeferredAdSessionListener;
        }
        throw new IllegalStateException("The AVID ad session is not deferred. Please ensure you are only using AvidDeferredAdSessionListener for deferred AVID ad session.");
    }

    public void registerFriendlyObstruction(View view) {
        InternalAvidAdSession internalAvidAdSessionFindInternalAvidAdSessionById = AvidManager.getInstance().findInternalAvidAdSessionById(getAvidAdSessionId());
        if (internalAvidAdSessionFindInternalAvidAdSessionById != null) {
            internalAvidAdSessionFindInternalAvidAdSessionById.getObstructionsWhiteList().add(view);
        }
    }
}
