package com.facebook.ads.redexgen.X;

import android.content.Context;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class JI {
    private JI() {
    }

    public static void C(Context ctx) {
        if (JA.u(ctx)) {
            D(ctx);
        }
    }

    private static void D(Context ctx) {
        new Thread(new JH(ctx)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void E(Context ctx) {
        try {
            F(ctx);
        } catch (Throwable th) {
        }
    }

    private static void F(Context ctx) {
        C0585Hx.D(ctx, "jni_test_result", C0584Hw.sB, new C0583Hv(JF.B(ctx).toString()));
    }
}
