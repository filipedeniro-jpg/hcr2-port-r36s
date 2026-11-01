package com.facebook.ads.redexgen.X;

import android.util.SparseArray;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.8i, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03378i {
    private final SparseArray<int[]> B = new SparseArray<>();

    public final boolean A(int id) {
        return this.B.indexOfKey(id) >= 0;
    }

    public final int[] B(int id) {
        return this.B.get(id);
    }

    public final void C(int id, int[] dimen) {
        this.B.put(id, dimen);
    }
}
