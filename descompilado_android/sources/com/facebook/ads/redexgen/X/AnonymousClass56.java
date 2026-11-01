package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.56, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AnonymousClass56 {
    private EnumC02053g B;
    private J1 C;
    private InterfaceC0612Iz D;
    private int E;
    private Map<Integer, String> F;
    private HashMap<Integer, Integer> G;
    private int H;
    private double I;
    private int J;
    private boolean K;
    private String L;
    private int M;

    private AnonymousClass56() {
    }

    public final J1 A() {
        return this.C;
    }

    public final EnumC02053g B() {
        return this.B;
    }

    public final InterfaceC0612Iz C() {
        return this.D;
    }

    public final int D() {
        return this.E;
    }

    @SuppressLint({"PublicMethodReturnMutableCollection"})
    public final Map<Integer, String> E() {
        return this.F;
    }

    @InterfaceC01641r
    public final Integer F(int signalId) {
        if (this.G == null) {
            return null;
        }
        return this.G.get(Integer.valueOf(signalId));
    }

    public final int G() {
        return this.H;
    }

    public final double H() {
        return this.I;
    }

    public final int I() {
        return this.J;
    }

    public final String J() {
        return this.L;
    }

    public final int K() {
        return this.M;
    }

    public final boolean L() {
        return this.K;
    }
}
