package com.integralads.avid.library.adcolony.session.internal;

import android.content.Context;
import com.integralads.avid.library.adcolony.session.ExternalAvidAdSessionContext;
import com.integralads.avid.library.adcolony.video.AvidVideoPlaybackListenerImpl;

/* JADX INFO: loaded from: classes2.dex */
public class InternalAvidManagedVideoAdSession extends InternalAvidManagedAdSession {
    private AvidVideoPlaybackListenerImpl a;

    public InternalAvidManagedVideoAdSession(Context context, String str, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        super(context, str, externalAvidAdSessionContext);
        this.a = new AvidVideoPlaybackListenerImpl(this, getAvidBridgeManager());
    }

    public AvidVideoPlaybackListenerImpl getAvidVideoPlaybackListener() {
        return this.a;
    }

    @Override // com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession
    public SessionType getSessionType() {
        return SessionType.MANAGED_VIDEO;
    }

    @Override // com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession
    public MediaType getMediaType() {
        return MediaType.VIDEO;
    }

    @Override // com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession
    public void onEnd() {
        this.a.destroy();
        super.onEnd();
    }
}
