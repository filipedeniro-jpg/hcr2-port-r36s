package com.facebook.ads.redexgen.X;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.Serializable;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2r, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C01902r implements Serializable {
    private double B;
    private double C;
    private double D;
    private double E;
    private double F;
    private double G;
    private double H;
    private int I;
    private double J;
    private double K;
    private double L;

    public C01902r(double mEligibleThreshold) {
        this.F = mEligibleThreshold;
    }

    public final double A() {
        return this.B;
    }

    public final double B() {
        return this.E;
    }

    public final double C() {
        return this.G;
    }

    public final double D() {
        return this.H;
    }

    public final double E() {
        return this.J;
    }

    public final double F() {
        return this.I == 0 ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE : this.K;
    }

    public final void G(double seconds, double value) {
        this.I++;
        this.J += seconds;
        this.D = value;
        this.L += value * seconds;
        this.B = this.L / this.J;
        this.K = Math.min(this.K, value);
        this.G = Math.max(this.G, value);
        if (value >= this.F) {
            this.E += seconds;
            this.C += seconds;
            this.H = Math.max(this.H, this.C);
            return;
        }
        this.C = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public final void H() {
        this.B = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.D = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.E = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.G = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.I = 0;
        this.J = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.K = 1.0d;
        this.L = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public final void I() {
        this.C = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }
}
