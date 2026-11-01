package com.facebook.ads.redexgen.X;

import android.util.SparseArray;
import java.util.ArrayList;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ef, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0490Ef {
    public SparseArray<C0489Ee> B = new SparseArray<>();
    private int C = 0;

    public final void A(C1G adapter) {
        this.C++;
    }

    private final void B() {
        for (int i = 0; i < this.B.size(); i++) {
            this.B.valueAt(i).E.clear();
        }
    }

    private final long C(long oldAverage, long newValue) {
        if (oldAverage == 0) {
            return newValue;
        }
        return (newValue / 4) + ((oldAverage / 4) * 3);
    }

    public final void C() {
        this.C--;
    }

    private C0489Ee D(int viewType) {
        C0489Ee c0489Ee = this.B.get(viewType);
        if (c0489Ee == null) {
            C0489Ee c0489Ee2 = new C0489Ee();
            this.B.put(viewType, c0489Ee2);
            return c0489Ee2;
        }
        return c0489Ee;
    }

    public final void D(int viewType, long bindTimeNs) {
        C0489Ee c0489EeD = D(viewType);
        c0489EeD.B = C(c0489EeD.B, bindTimeNs);
    }

    public final void E(int viewType, long createTimeNs) {
        C0489Ee c0489EeD = D(viewType);
        c0489EeD.C = C(c0489EeD.C, createTimeNs);
    }

    public final AbstractC0502Er F(int viewType) {
        C0489Ee c0489Ee = this.B.get(viewType);
        if (c0489Ee == null || c0489Ee.E.isEmpty()) {
            return null;
        }
        ArrayList<AbstractC0502Er> arrayList = c0489Ee.E;
        return arrayList.remove(arrayList.size() - 1);
    }

    public final void G(C1G oldAdapter, C1G newAdapter, boolean compatibleWithPrevious) {
        if (oldAdapter != null) {
            C();
        }
        if (!compatibleWithPrevious && this.C == 0) {
            B();
        }
        if (newAdapter != null) {
            A(newAdapter);
        }
    }

    public final void H(AbstractC0502Er scrap) {
        int iJ = scrap.J();
        ArrayList<AbstractC0502Er> arrayList = D(iJ).E;
        if (this.B.get(iJ).D <= arrayList.size()) {
            return;
        }
        scrap.a();
        arrayList.add(scrap);
    }

    public final boolean I(int viewType, long approxCurrentNs, long deadlineNs) {
        long j = D(viewType).B;
        return j == 0 || approxCurrentNs + j < deadlineNs;
    }

    public final boolean J(int viewType, long approxCurrentNs, long deadlineNs) {
        long j = D(viewType).C;
        return j == 0 || approxCurrentNs + j < deadlineNs;
    }
}
