package com.integralads.avid.library.inmobi.session;

import com.integralads.avid.library.inmobi.AvidManager;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidManagedVideoAdSession;
import com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener;

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
