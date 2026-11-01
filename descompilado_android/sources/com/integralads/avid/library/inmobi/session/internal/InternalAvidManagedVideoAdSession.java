package com.integralads.avid.library.inmobi.session.internal;

import android.content.Context;
import com.integralads.avid.library.inmobi.session.ExternalAvidAdSessionContext;
import com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListenerImpl;

/* JADX INFO: loaded from: classes2.dex */
public class InternalAvidManagedVideoAdSession extends InternalAvidManagedAdSession {
    private AvidVideoPlaybackListenerImpl avidVideoPlaybackListener;

    public InternalAvidManagedVideoAdSession(Context context, String str, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        super(context, str, externalAvidAdSessionContext);
        this.avidVideoPlaybackListener = new AvidVideoPlaybackListenerImpl(this, getAvidBridgeManager());
    }

    public AvidVideoPlaybackListenerImpl getAvidVideoPlaybackListener() {
        return this.avidVideoPlaybackListener;
    }

    @Override // com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession
    public SessionType getSessionType() {
        return SessionType.MANAGED_VIDEO;
    }

    @Override // com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession
    public MediaType getMediaType() {
        return MediaType.VIDEO;
    }

    @Override // com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession
    public void onEnd() {
        this.avidVideoPlaybackListener.destroy();
        super.onEnd();
    }
}
