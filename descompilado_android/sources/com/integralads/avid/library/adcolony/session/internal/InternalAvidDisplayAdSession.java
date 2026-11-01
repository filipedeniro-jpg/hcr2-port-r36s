package com.integralads.avid.library.adcolony.session.internal;

import android.content.Context;
import com.integralads.avid.library.adcolony.session.ExternalAvidAdSessionContext;

/* JADX INFO: loaded from: classes2.dex */
public class InternalAvidDisplayAdSession extends InternalAvidHtmlAdSession {
    public InternalAvidDisplayAdSession(Context context, String str, ExternalAvidAdSessionContext externalAvidAdSessionContext) {
        super(context, str, externalAvidAdSessionContext);
    }

    @Override // com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession
    public SessionType getSessionType() {
        return SessionType.DISPLAY;
    }

    @Override // com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession
    public MediaType getMediaType() {
        return MediaType.DISPLAY;
    }
}
