package com.integralads.avid.library.inmobi.session.internal;

import android.content.Context;
import com.integralads.avid.library.inmobi.session.ExternalAvidAdSessionContext;

/* JADX INFO: loaded from: classes2.dex */
public class InternalAvidManagedDisplayAdSession extends InternalAvidManagedAdSession {
    public InternalAvidManagedDisplayAdSession(Context context, String str, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        super(context, str, externalAvidAdSessionContext);
    }

    @Override // com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession
    public SessionType getSessionType() {
        return SessionType.MANAGED_DISPLAY;
    }

    @Override // com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession
    public MediaType getMediaType() {
        return MediaType.DISPLAY;
    }
}
