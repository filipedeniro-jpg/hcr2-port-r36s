package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class DW {
    public int B;
    public int C;
    public AbstractC0502Er D;
    public AbstractC0502Er E;
    public int F;
    public int G;

    private DW(AbstractC0502Er oldHolder, AbstractC0502Er newHolder) {
        this.E = oldHolder;
        this.D = newHolder;
    }

    public DW(AbstractC0502Er oldHolder, AbstractC0502Er newHolder, int fromX, int fromY, int toX, int toY) {
        this(oldHolder, newHolder);
        this.B = fromX;
        this.C = fromY;
        this.F = toX;
        this.G = toY;
    }

    public final String toString() {
        return "ChangeInfo{oldHolder=" + this.E + ", newHolder=" + this.D + ", fromX=" + this.B + ", fromY=" + this.C + ", toX=" + this.F + ", toY=" + this.G + '}';
    }
}
