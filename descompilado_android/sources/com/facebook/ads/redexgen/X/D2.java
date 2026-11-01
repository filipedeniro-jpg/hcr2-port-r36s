package com.facebook.ads.redexgen.X;

import android.text.TextUtils;
import java.util.HashMap;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class D2 extends C1B {
    public final /* synthetic */ D1 B;

    public D2(D1 this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.C1B
    public final void B() {
        if (!this.B.W.D()) {
            this.B.W.G();
            HashMap map = new HashMap();
            if (!TextUtils.isEmpty(((AbstractC0466Dh) this.B).C.C())) {
                this.B.a.A(map);
                map.put("touch", MJ.C(this.B.W.m258B()));
                this.B.C(map);
                ((AbstractC0466Dh) this.B).D.lC(((AbstractC0466Dh) this.B).C.C(), map);
                if (this.B.getAudienceNetworkListener() != null) {
                    this.B.getAudienceNetworkListener().aB("com.facebook.ads.interstitial.impression.logged");
                }
            }
        }
    }
}
