package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.VisibleForTesting;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ij, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0597Ij {
    private static C0597Ij B = new C0597Ij();

    @VisibleForTesting
    private C0597Ij() {
    }

    @SuppressLint({"CatchGeneralException"})
    public final Map<String, String> A(Context appContext) {
        try {
            return B(appContext, false).CC();
        } catch (Throwable th) {
            C0585Hx.C(th);
            return C0591Id.B(appContext);
        }
    }

    @SuppressLint({"InstanceMethodCanBeStatic"})
    public final InterfaceC0596Ii B(Context context, boolean block) {
        return new C0599Il(context, block);
    }

    public static C0597Ij B() {
        return B;
    }
}
