package com.facebook.ads.redexgen.X;

import android.os.Build;
import android.widget.EdgeEffect;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class BX {
    private static final BV B;

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            B = new BW();
        } else {
            B = new BV();
        }
    }

    public static void B(EdgeEffect edgeEffect, float deltaDistance, float displacement) {
        B.A(edgeEffect, deltaDistance, displacement);
    }
}
