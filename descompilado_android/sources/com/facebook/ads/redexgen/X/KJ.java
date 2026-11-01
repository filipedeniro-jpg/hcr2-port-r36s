package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public enum KJ {
    HEIGHT_100(-1, 100, 1, 0),
    HEIGHT_120(-1, 120, 2, 1),
    HEIGHT_300(-1, 300, 3, 2),
    HEIGHT_400(-1, 400, 4, 3),
    HEIGHT_50(-1, 50, 5, 4),
    RECT_DYNAMIC(-1, -1, 6, 5);

    private final int B;
    private final int C;
    private final int D;
    private final int E;

    KJ(int width, int height, int value, int enumCode) {
        this.E = width;
        this.C = height;
        this.D = value;
        this.B = enumCode;
    }

    public final int A() {
        return this.C;
    }

    public final int B() {
        return this.D;
    }

    public static KJ B(int enumCode) {
        for (KJ kj : values()) {
            if (kj.B == enumCode) {
                return kj;
            }
        }
        return null;
    }

    public final int C() {
        return this.E;
    }
}
