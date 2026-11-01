package com.facebook.ads.redexgen.X;

import com.facebook.share.internal.MessengerShareContentUtility;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2z, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public enum EnumC01982z {
    REPORT("report"),
    HIDE(MessengerShareContentUtility.SHARE_BUTTON_HIDE),
    NONE("none");

    private final String B;

    EnumC01982z(String type) {
        this.B = type;
    }

    public final String A() {
        return this.B;
    }
}
