package com.facebook.ads.redexgen.X;

import android.content.Intent;
import android.view.ViewGroup;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ft, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0530Ft extends C0526Fp {
    private C0530Ft(C0531Fu activityImpl) {
        super(activityImpl);
    }

    @Override // com.facebook.ads.redexgen.X.C0526Fp, com.facebook.ads.redexgen.X.InterfaceC0525Fo
    public final void aB(String event) {
        if (this.B.get() == null) {
            return;
        }
        this.B.get().S(event);
        String strB = EnumC02926p.REWARDED_VIDEO_END_ACTIVITY.B();
        String strB2 = EnumC02926p.REWARDED_VIDEO_ERROR.B();
        if (!event.equals(strB) && !event.equals(strB2)) {
            return;
        }
        this.B.get().finish();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.ads.redexgen.X.C0526Fp, com.facebook.ads.redexgen.X.InterfaceC0525Fo
    public final void bB(String event, It eventObj) {
        C0524Fn c0524Fn = null;
        super.bB(event, eventObj);
        if (this.B.get() == null) {
            return;
        }
        C0531Fu c0531Fu = this.B.get();
        if (!event.equals(EnumC02926p.REWARDED_VIDEO_CHOOSE_YOUR_OWN_AD.B())) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("rewardedVideoAdDataBundle", ((H8) eventObj).A());
        InterfaceC0578Hq interfaceC0578HqT = new C0527Fq(c0531Fu, intent, JX.E(c0531Fu.B()), c0524Fn).T();
        if (c0531Fu.E != null) {
            c0531Fu.E.A();
        }
        c0531Fu.E = null;
        J6.G((ViewGroup) interfaceC0578HqT);
        c0531Fu.I = interfaceC0578HqT;
        interfaceC0578HqT.bC(c0531Fu.C(), null, c0531Fu);
    }
}
