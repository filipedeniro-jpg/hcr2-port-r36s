package com.integralads.avid.library.adcolony.session.internal;

/* JADX INFO: loaded from: classes2.dex */
public interface InternalAvidAdSessionListener {
    void sessionDidEnd(InternalAvidAdSession internalAvidAdSession);

    void sessionHasBecomeActive(InternalAvidAdSession internalAvidAdSession);

    void sessionHasResignedActive(InternalAvidAdSession internalAvidAdSession);
}
