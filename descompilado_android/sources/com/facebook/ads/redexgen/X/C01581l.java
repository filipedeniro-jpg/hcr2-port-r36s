package com.facebook.ads.redexgen.X;

import com.my.target.aa;
import java.io.Serializable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1l, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01581l implements Serializable {
    private static final long serialVersionUID = 1;
    private final C1N B;
    private final C1R C;
    private final C1V D;
    private final int E;
    private final String F;
    private final C1Z G;
    private final String H;
    private final boolean I;
    private final C01551i J;
    private final String K;
    private final int L;
    private final C01631q M;

    private C01581l(String requestId, String clientToken, C01551i pageDetails, C1V adMetadata, C1Z ctaData, C1N adColorsData, C1R adMediaData, String format, C01631q toolbarDetails, boolean isRewardedAd, int secondsForReward, int anLogoType) {
        this.K = requestId;
        this.F = clientToken;
        this.J = pageDetails;
        this.D = adMetadata;
        this.G = ctaData;
        this.B = adColorsData;
        this.C = adMediaData;
        this.H = format;
        this.I = isRewardedAd;
        this.M = toolbarDetails;
        this.L = secondsForReward;
        this.E = anLogoType;
    }

    public final C1N A() {
        return this.B;
    }

    public final C1R B() {
        return this.C;
    }

    public static C01581l B(C01481b adDataBundle) {
        C01491c c01491c = adDataBundle.A().get(0);
        return new C01581l(adDataBundle.J(), adDataBundle.C(), adDataBundle.I(), c01491c.D(), c01491c.A(), adDataBundle.D(), c01491c.C(), aa.e.bo, adDataBundle.K(), adDataBundle.A().get(0).E(), adDataBundle.A().get(0).C().E(), adDataBundle.B());
    }

    public final C1V C() {
        return this.D;
    }

    public static C01581l C(C01601n adDataBundle) {
        return new C01581l(adDataBundle.Q(), adDataBundle.A(), adDataBundle.P(), adDataBundle.L(), adDataBundle.N(), adDataBundle.J(), adDataBundle.K(), "rewarded_video", adDataBundle.R(), true, adDataBundle.K().E(), adDataBundle.M());
    }

    public final int D() {
        return this.E;
    }

    public final String E() {
        return this.F;
    }

    public final C1Z F() {
        return this.G;
    }

    public final String G() {
        return this.H;
    }

    public final C01551i H() {
        return this.J;
    }

    public final String I() {
        return this.K;
    }

    public final int J() {
        return this.L;
    }

    public final C01631q K() {
        return this.M;
    }

    public final boolean L() {
        return this.I;
    }
}
