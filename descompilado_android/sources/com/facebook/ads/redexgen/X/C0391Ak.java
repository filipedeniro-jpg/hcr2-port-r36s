package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ak, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0391Ak {
    private static final int B = J6.C.heightPixels;
    private static final int C = J6.C.widthPixels;

    private C0391Ak() {
    }

    public static float B(C01491c adInfo) {
        int iC = adInfo.C().C();
        int width = adInfo.C().A();
        if (width > 0) {
            return iC / width;
        }
        return -1.0f;
    }

    public static boolean C(double aspectRatio) {
        return aspectRatio < 0.9d;
    }

    public static boolean D(int orientation, int topMargin, double aspectRatio) {
        return orientation == 2 || G(aspectRatio, topMargin);
    }

    private static int E(int topMargin) {
        int ctaSpacing = J6.B(16);
        int ctaTextHeight = B1.G;
        int i = ctaTextHeight * 2;
        int ctaTextHeight2 = AbstractC0390Aj.E;
        int ctaMargin = ctaTextHeight2 * 2;
        int ctaSpacing2 = ctaSpacing + i + ctaMargin;
        int ctaMargin2 = B;
        return (ctaMargin2 - topMargin) - ctaSpacing2;
    }

    private static int F(double aspectRatio) {
        return (int) (((double) (C - (AbstractC0390Aj.E * 2))) / aspectRatio);
    }

    private static boolean G(double aspectRatio, int topMargin) {
        return E(topMargin) < F(aspectRatio);
    }
}
