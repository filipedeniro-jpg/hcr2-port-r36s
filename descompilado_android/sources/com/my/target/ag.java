package com.my.target;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: ClickArea.java */
/* JADX INFO: loaded from: classes2.dex */
public class ag {
    private static final int cA = 64;
    private static final int cB = 128;
    private static final int cC = 256;
    private static final int cD = 512;
    private static final int cE = 1024;
    private static final int cF = 2048;
    private static final int cG = 4096;

    @NonNull
    public static final ag cs = new ag(4096);

    @NonNull
    public static final ag ct = new ag(64);
    private static final int cu = 1;
    private static final int cv = 2;
    private static final int cw = 4;
    private static final int cx = 8;
    private static final int cy = 16;
    private static final int cz = 32;
    public final boolean cH;
    public final boolean cI;
    public final boolean cJ;
    public final boolean cK;
    public final boolean cL;
    public final boolean cM;
    public final boolean cN;
    public final boolean cO;
    public final boolean cP;
    public final boolean cQ;
    public final boolean cR;
    public final boolean cS;
    public final boolean cT;
    private final int cU;

    @NonNull
    public static ag c(int i) {
        return new ag(i);
    }

    private ag(int i) {
        this.cU = i;
        this.cH = (i & 1) == 1;
        this.cI = (i & 2) == 2;
        this.cJ = (i & 4) == 4;
        this.cK = (i & 8) == 8;
        this.cL = (i & 16) == 16;
        this.cM = (i & 32) == 32;
        this.cN = (i & 64) == 64;
        this.cO = (i & 128) == 128;
        this.cP = (i & 256) == 256;
        this.cQ = (i & 512) == 512;
        this.cR = (i & 1024) == 1024;
        this.cS = (i & 2048) == 2048;
        this.cT = (i & 4096) == 4096;
    }

    public int O() {
        return this.cU;
    }
}
