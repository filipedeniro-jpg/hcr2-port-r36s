package com.facebook.ads.redexgen.X;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3j, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public enum EnumC02083j {
    BENIGN_IGNORE(0),
    SUSPICIOUS(1024),
    PARANOID(2048),
    BENIGN_COLLECT(4096);

    private static final Map<Integer, EnumC02083j> H = new HashMap();
    private int B;

    static {
        for (EnumC02083j enumC02083j : values()) {
            H.put(Integer.valueOf(enumC02083j.B), enumC02083j);
        }
    }

    EnumC02083j(int value) {
        this.B = value;
    }

    public final int A() {
        return this.B;
    }

    public static EnumC02083j B(int i) {
        EnumC02083j enumC02083j = H.get(Integer.valueOf(i));
        return enumC02083j == null ? BENIGN_IGNORE : enumC02083j;
    }
}
