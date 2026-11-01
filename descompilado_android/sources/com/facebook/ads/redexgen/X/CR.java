package com.facebook.ads.redexgen.X;

import java.util.HashMap;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class CR implements F1 {
    public final /* synthetic */ CO B;

    public CR(CO this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.F1
    public final void jD() {
        this.B.P.A(EnumC0617Je.AD_CLOSE_CLICKED, null);
        if (!this.B.c.C(this.B.getContext())) {
            if (this.B.f || this.B.j == null) {
                if (!this.B.f || this.B.G == null) {
                    return;
                }
                this.B.G.aB(EnumC02926p.REWARDED_VIDEO_END_ACTIVITY.B());
                return;
            }
            this.B.f = true;
            this.B.j.m243N();
            return;
        }
        HashMap map = new HashMap();
        this.B.k.A(map);
        map.put("touch", MJ.C(this.B.c.m258B()));
        this.B.E.hC(this.B.B.A(), map);
    }
}
