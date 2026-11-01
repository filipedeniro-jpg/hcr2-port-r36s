package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public enum B6 {
    UNSPECIFIED(0),
    PORTRAIT(1),
    LANDSCAPE(2);

    private int B;

    B6(int id) {
        this.B = id;
    }

    public final int A() {
        return this.B;
    }

    public static B6 B(int id) {
        for (B6 b6 : values()) {
            if (b6.B == id) {
                return b6;
            }
        }
        return PORTRAIT;
    }
}
