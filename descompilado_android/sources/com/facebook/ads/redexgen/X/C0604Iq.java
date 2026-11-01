package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.WorkerThread;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Iq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0604Iq {
    private C0604Iq() {
    }

    @WorkerThread
    public static void B(Context context) {
        if (context == null) {
            return;
        }
        LI.B(new C0603Ip(context.getApplicationContext()));
    }
}
