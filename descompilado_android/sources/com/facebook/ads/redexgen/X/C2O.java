package com.facebook.ads.redexgen.X;

import android.os.StrictMode;
import java.util.UUID;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2O, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C2O {

    @InterfaceC01641r
    private static String B = null;

    private C2O() {
    }

    public static String B() {
        if (B == null) {
            StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
            try {
                B = UUID.randomUUID().toString();
            } finally {
                StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            }
        }
        return B;
    }
}
