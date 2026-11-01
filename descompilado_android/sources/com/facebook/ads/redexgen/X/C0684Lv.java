package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Lv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0684Lv {
    private C0684Lv() {
    }

    public static <T> T B(T t, String errorMessage) {
        if (t == null) {
            throw new NullPointerException(errorMessage);
        }
        return t;
    }

    public static <T> T C(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public static void D(boolean expression, String errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
