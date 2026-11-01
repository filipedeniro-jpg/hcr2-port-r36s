package com.inmobi.ads;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: compiled from: BannerRefreshHandler.java */
/* JADX INFO: loaded from: classes2.dex */
final class q extends Handler {
    private InMobiBanner a;

    q(InMobiBanner inMobiBanner) {
        this.a = inMobiBanner;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        if (message.what != 1) {
            return;
        }
        this.a.a(true);
    }
}
