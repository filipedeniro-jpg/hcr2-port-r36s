package com.facebook.ads.redexgen.X;

import android.view.MotionEvent;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8p, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03448p {
    private C03448p() {
    }

    public static boolean B(MotionEvent event, int source) {
        return (event.getSource() & source) == source;
    }
}
