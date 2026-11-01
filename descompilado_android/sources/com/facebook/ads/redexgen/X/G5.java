package com.facebook.ads.redexgen.X;

import com.facebook.ads.internal.api.NativeAdBaseApi;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class G5 implements C1D {
    public final /* synthetic */ GB B;
    public final /* synthetic */ NativeAdBaseApi C;

    public G5(GB this$0, NativeAdBaseApi nativeAdBaseApi) {
        this.B = this$0;
        this.C = nativeAdBaseApi;
    }

    @Override // com.facebook.ads.redexgen.X.C1D
    public final void FE(boolean success) {
        ((KF) this.C).Q(success, true);
    }
}
