package com.facebook.ads.redexgen.X;

import android.text.TextUtils;
import java.util.HashMap;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class CT extends C1B {
    public final /* synthetic */ CO B;

    public CT(CO this$0) {
        this.B = this$0;
    }

    @Override // com.facebook.ads.redexgen.X.C1B
    public final void B() {
        if (!this.B.c.D()) {
            this.B.c.G();
            HashMap map = new HashMap();
            if (!TextUtils.isEmpty(this.B.B.A())) {
                this.B.k.A(map);
                map.put("touch", MJ.C(this.B.c.m258B()));
                if (this.B.B.C() != null) {
                    map.put("extra_hints", this.B.B.C());
                }
                map.put("is_cyoa", String.valueOf(this.B.B.S()));
                if (this.B.h != null) {
                    map.put("video_source", this.B.h.B);
                }
                this.B.E.lC(this.B.B.A(), map);
            }
            if (this.B.G != null) {
                this.B.G.aB(EnumC02926p.REWARDED_VIDEO_IMPRESSION.B());
            }
        }
    }
}
