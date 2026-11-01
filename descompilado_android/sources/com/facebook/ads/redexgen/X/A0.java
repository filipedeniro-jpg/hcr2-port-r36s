package com.facebook.ads.redexgen.X;

import java.util.HashMap;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class A0 implements F1 {
    public final /* synthetic */ C03719q B;

    public A0(C03719q this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.F1
    public final void jD() {
        this.B.G.A(EnumC0617Je.AD_CLOSE_CLICKED, null);
        if (this.B.K.get() != null) {
            ((CJ) this.B.K.get()).B();
            if (this.B.P.getVisibility() != 0) {
                HashMap map = new HashMap();
                this.B.C.A(map);
                map.put("touch", MJ.C(this.B.O.m258B()));
                this.B.B.ED(this.B.M, map);
            }
        }
    }
}
