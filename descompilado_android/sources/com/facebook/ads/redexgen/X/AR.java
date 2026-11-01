package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class AR {
    public final Object B;

    public AR(Object info) {
        this.B = info;
    }

    public static AR B(int rowIndex, int rowSpan, int columnIndex, int columnSpan, boolean heading, boolean selected) {
        return new AR(AT.D.C(rowIndex, rowSpan, columnIndex, columnSpan, heading, selected));
    }
}
