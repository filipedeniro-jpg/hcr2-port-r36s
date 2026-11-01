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
public final class H5 {
    private static final String C = H5.class.getSimpleName();
    private static volatile H5 D;
    private final Future<H4> B;

    private H5(Context context) {
        this.B = Executors.newSingleThreadExecutor().submit(new H3(this, context));
    }

    public final boolean A(H0 cacheFileData) {
        H4 h4D = D();
        return h4D != null && h4D.A(cacheFileData);
    }

    @Nullable
    public final String B(String url) {
        H4 h4D = D();
        if (h4D == null) {
            return null;
        }
        return h4D.m253B(url);
    }

    public static H5 C(Context context) {
        if (D == null) {
            synchronized (HG.class) {
                if (D == null) {
                    D = new H5(context.getApplicationContext());
                }
            }
        }
        return D;
    }

    @Nullable
    private H4 D() {
        try {
            return this.B.get(500L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            Log.e(C, "Timed out waiting for cache server.", e);
            return null;
        }
    }
}
