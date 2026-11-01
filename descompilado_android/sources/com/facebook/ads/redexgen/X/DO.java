package com.facebook.ads.redexgen.X;

import java.util.ArrayList;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class DO implements Runnable {
    public final /* synthetic */ C0459Da B;
    public final /* synthetic */ ArrayList C;

    public DO(C0459Da this$0, ArrayList arrayList) {
        this.B = this$0;
        this.C = arrayList;
    }

    @Override // java.lang.Runnable
    public final void run() {
        for (DX dx : this.C) {
            this.B.k(dx.D, dx.B, dx.C, dx.E, dx.F);
        }
        this.C.clear();
        this.B.G.remove(this.C);
    }
}
