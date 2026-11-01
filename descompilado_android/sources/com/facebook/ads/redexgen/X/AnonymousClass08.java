package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.08, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public enum AnonymousClass08 {
    CANNOT_OPEN,
    CANNOT_TRACK;

    public static boolean B(@Nullable AnonymousClass08 outcome) {
        return CANNOT_OPEN.equals(outcome) || CANNOT_TRACK.equals(outcome);
    }
}
