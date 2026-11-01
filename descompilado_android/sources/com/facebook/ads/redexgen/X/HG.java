package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class HG {
    private static final String C = HG.class.getSimpleName();
    private static volatile HG D;
    private Future<C0679Lq> B;

    private HG(Context context) {
        this.B = Executors.newSingleThreadExecutor().submit(new HC(this, context));
    }

    public final boolean A(H0 videoData) {
        C0679Lq c0679LqC = C();
        boolean z = c0679LqC != null && c0679LqC.B(videoData.F, new HE(this, videoData));
        if (!z) {
            D();
        }
        return z;
    }

    public static HG B(Context context) {
        if (D == null) {
            Context context2 = context.getApplicationContext();
            synchronized (HG.class) {
                if (D == null) {
                    D = new HG(context2);
                }
                C0679Lq.G(new HB());
            }
        }
        return D;
    }

    @Nullable
    public final String B(String url) {
        C0679Lq c0679LqC = C();
        if (c0679LqC == null) {
            return null;
        }
        return c0679LqC.A(url);
    }

    @Nullable
    private C0679Lq C() {
        try {
            return this.B.get(1500L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            Log.e(C, "Timed out waiting for cache server.", e);
            return null;
        }
    }

    private void D() {
        Context contextB = HM.B();
        if (contextB != null && JA.EB(contextB)) {
            this.B = Executors.newSingleThreadExecutor().submit(new HF(this, contextB));
        }
    }
}
