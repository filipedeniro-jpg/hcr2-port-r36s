package com.facebook.ads.redexgen.X;

import java.util.HashMap;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ds, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0477Ds extends C1B {
    public final /* synthetic */ C0465Dg B;

    public C0477Ds(C0465Dg this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.C1B
    public final void B() {
        if (!this.B.D.D()) {
            this.B.D.G();
            HashMap map = new HashMap();
            this.B.E.A(map);
            map.put("touch", MJ.C(this.B.D.m258B()));
            this.B.C(map);
            ((AbstractC0466Dh) this.B).D.lC(((AbstractC0466Dh) this.B).C.C(), map);
            if (this.B.getAudienceNetworkListener() != null) {
                this.B.getAudienceNetworkListener().aB("com.facebook.ads.interstitial.impression.logged");
            }
        }
    }
}
