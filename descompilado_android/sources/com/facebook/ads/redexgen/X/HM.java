package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class HM {

    @Nullable
    private static Context B;

    private HM() {
    }

    @Nullable
    public static Context B() {
        return B;
    }

    public static void C(Context context) {
        if (context == null) {
            return;
        }
        B = context.getApplicationContext();
    }
}
