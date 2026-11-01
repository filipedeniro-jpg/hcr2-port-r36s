package com.facebook.ads.redexgen.X;

import android.content.Context;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class C3 {
    private final Context B;
    private EnumC0606Is C;
    private int D;
    private C1 E;
    private String F;
    private String K;
    private String L;
    private boolean J = true;
    private boolean M = true;
    private boolean I = true;
    private boolean G = true;
    private boolean H = true;

    public C3(Context context) {
        this.B = context;
    }

    public final C2 A() {
        return new C2(this, null);
    }

    public final C3 B(EnumC0606Is icon) {
        this.C = icon;
        return this;
    }

    public final C3 C(int iconBackground) {
        this.D = iconBackground;
        return this;
    }

    public final C3 D(C1 navigationManager) {
        this.E = navigationManager;
        return this;
    }

    public final C3 E(String optionTitle) {
        this.F = optionTitle;
        return this;
    }

    public final C3 F(boolean shouldCloseAd) {
        this.G = shouldCloseAd;
        return this;
    }

    public final C3 G(boolean showChipView) {
        this.I = showChipView;
        return this;
    }

    public final C3 H(boolean showCloseButton) {
        this.J = showCloseButton;
        return this;
    }

    public final C3 I(boolean shouldShowFooterView) {
        this.H = shouldShowFooterView;
        return this;
    }

    public final C3 J(String subtitle) {
        this.K = subtitle;
        return this;
    }

    public final C3 K(String title) {
        this.L = title;
        return this;
    }

    public final C3 L(boolean useLargeIcon) {
        this.M = useLargeIcon;
        return this;
    }
}
