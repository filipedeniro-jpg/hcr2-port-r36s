package com.integralads.avid.library.adcolony.session;

import com.integralads.avid.library.adcolony.AvidManager;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidManagedVideoAdSession;
import com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListener;

/* JADX INFO: loaded from: classes2.dex */
public class AvidManagedVideoAdSession extends AbstractAvidManagedAdSession {
    public AvidVideoPlaybackListener getAvidVideoPlaybackListener() {
        InternalAvidManagedVideoAdSession internalAvidManagedVideoAdSession = (InternalAvidManagedVideoAdSession) AvidManager.getInstance().findInternalAvidAdSessionById(getAvidAdSessionId());
        if (internalAvidManagedVideoAdSession != null) {
            return internalAvidManagedVideoAdSession.getAvidVideoPlaybackListener();
        }
        return null;
    }
}
