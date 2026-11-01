package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1Q, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C1Q {
    private int B;
    private String C;
    private int D;
    private boolean E;
    private boolean F;
    private C01571k G;
    private int H;
    private int I;
    private int J;
    private String K;

    public final C1Q A(boolean isAudioMuted) {
        this.E = isAudioMuted;
        return this;
    }

    public final C1Q B(boolean autoplay) {
        this.F = autoplay;
        return this;
    }

    public final C1R C() {
        return new C1R(this);
    }

    public final C1Q D(int imageHeight) {
        this.B = imageHeight;
        return this;
    }

    public final C1Q E(String imageUrl) {
        this.C = imageUrl;
        return this;
    }

    public final C1Q F(int imageWidth) {
        this.D = imageWidth;
        return this;
    }

    public final C1Q G(@Nullable C01571k playableAdData) {
        this.G = playableAdData;
        return this;
    }

    public final C1Q H(int secondsForReward) {
        this.H = secondsForReward;
        return this;
    }

    public final C1Q I(int unskippableSeconds) {
        this.I = unskippableSeconds;
        return this;
    }

    public final C1Q J(int durationSeconds) {
        this.J = durationSeconds;
        return this;
    }

    public final C1Q K(String videoUrl) {
        this.K = videoUrl;
        return this;
    }
}
