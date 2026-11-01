package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.4Y, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C4Y extends C4Z implements InterfaceC02294e {
    private final AnonymousClass56 B;
    private C02404p C;
    private final Map<Integer, C02113m> D;

    @SuppressLint({"UseSparseArrays"})
    public C4Y(AnonymousClass56 baseBDConfig) {
        super(baseBDConfig);
        this.D = new HashMap();
        this.B = baseBDConfig;
    }

    public final void B(C02113m bdSignal) {
        this.D.put(Integer.valueOf(bdSignal.C()), bdSignal);
    }

    public final void C(EnumC02083j reputationTier, Context context) {
        this.C = new C02404p(this.B, context, this.D);
        this.C.A();
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC02294e
    public final void WF() {
        if (this.C != null) {
            this.C.B();
        }
    }
}
