package com.facebook.ads.redexgen.X;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class DQ implements Runnable {
    public final /* synthetic */ C0459Da B;
    public final /* synthetic */ ArrayList C;

    public DQ(C0459Da this$0, ArrayList arrayList) {
        this.B = this$0;
        this.C = arrayList;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Iterator it = this.C.iterator();
        while (it.hasNext()) {
            this.B.i((AbstractC0502Er) it.next());
        }
        this.C.clear();
        this.B.C.remove(this.C);
    }
}
