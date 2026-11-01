package com.facebook.ads.redexgen.X;

import com.facebook.ads.NativeAd;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class G6 implements C1F {
    public final /* synthetic */ GB B;
    public final /* synthetic */ NativeAd C;

    public G6(GB this$0, NativeAd nativeAd) {
        this.B = this$0;
        this.C = nativeAd;
    }

    @Override // com.facebook.ads.redexgen.X.C1F
    public final void eD() {
        ((KF) this.C.getInternalNativeAd()).Q(true, true);
    }
}
