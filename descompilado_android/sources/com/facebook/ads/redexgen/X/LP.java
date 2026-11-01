package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class LP {
    public byte[] B;
    public String C;
    public LR D;
    public String E;

    public LP(String path, LW params) {
        this.E = "";
        if (path != null) {
            this.E = path;
        }
        if (params != null) {
            this.E += "?" + params.D();
        }
    }

    public final byte[] A() {
        return this.B;
    }

    public final String B() {
        return this.C;
    }

    public final LR C() {
        return this.D;
    }

    public final String D() {
        return this.E;
    }
}
