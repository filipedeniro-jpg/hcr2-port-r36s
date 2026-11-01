package com.facebook.ads.redexgen.X;

import com.facebook.ads.NativeAd;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class G7 implements C1D {
    public final /* synthetic */ GB B;
    public final /* synthetic */ NativeAd C;

    public G7(GB this$0, NativeAd nativeAd) {
        this.B = this$0;
        this.C = nativeAd;
    }

    @Override // com.facebook.ads.redexgen.X.C1D
    public final void FE(boolean success) {
        ((KF) this.C.getInternalNativeAd()).Q(success, true);
    }
}
