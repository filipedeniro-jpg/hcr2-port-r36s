package com.facebook.ads.redexgen.X;

import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class JP {
    private Map<String, String> B;
    private JZ C;
    private String D;
    private double E;
    private boolean F;
    private boolean G;
    private String H;
    private EnumC0613Ja I;

    public final JQ A() {
        return new JQ(this.H, this.E, this.D, this.B, this.C, this.I, this.F, this.G);
    }

    public final JP B(Map<String, String> mData) {
        this.B = mData;
        return this;
    }

    public final JP C(JZ mPriority) {
        this.C = mPriority;
        return this;
    }

    public final JP D(String mSessionId) {
        this.D = mSessionId;
        return this;
    }

    public final JP E(double mSessionTime) {
        this.E = mSessionTime;
        return this;
    }

    public final JP F(boolean mShouldAppendAnalogData) {
        this.F = mShouldAppendAnalogData;
        return this;
    }

    public final JP G(boolean shouldIncludeInFunnelLogging) {
        this.G = shouldIncludeInFunnelLogging;
        return this;
    }

    public final JP H(String mToken) {
        this.H = mToken;
        return this;
    }

    public final JP I(EnumC0613Ja mType) {
        this.I = mType;
        return this;
    }
}
