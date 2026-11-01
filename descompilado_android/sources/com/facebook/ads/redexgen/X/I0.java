package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class I0 {

    @Nullable
    private static I0 D;
    private final C0587Hz B;
    private final I1 C;

    private I0(Context context, Executor threadPoolExecutor, IP placement) {
        this.B = new C0587Hz(context);
        this.C = new I1(threadPoolExecutor, placement, context);
    }

    public static void B(Context context, Executor executor, IP placement) {
        if (!JA.x(context)) {
            return;
        }
        if (D == null) {
            D = new I0(context, executor, placement);
            D.C();
        } else {
            D.D(placement);
        }
    }

    private void C() {
        this.B.A(this.C);
    }

    private void D(IP placement) {
        this.C.G(placement);
    }
}
