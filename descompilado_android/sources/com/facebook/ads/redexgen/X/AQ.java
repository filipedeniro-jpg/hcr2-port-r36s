package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class AQ {
    public final Object B;

    public AQ(Object info) {
        this.B = info;
    }

    public static AQ B(int rowCount, int columnCount, boolean hierarchical, int selectionMode) {
        return new AQ(AT.D.D(rowCount, columnCount, hierarchical, selectionMode));
    }
}
