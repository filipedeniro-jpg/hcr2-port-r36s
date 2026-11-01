package com.facebook.ads.redexgen.X;

import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdListener;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class K5 implements K4 {
    public final /* synthetic */ KF B;
    public final /* synthetic */ NativeAdBase C;
    public final /* synthetic */ NativeAdListener D;

    public K5(KF this$0, NativeAdListener nativeAdListener, NativeAdBase nativeAdBase) {
        this.B = this$0;
        this.D = nativeAdListener;
        this.C = nativeAdBase;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0626Jn
    public final void MD() {
        this.D.onAdClicked(this.C);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0626Jn
    public final void PD() {
        this.D.onAdLoaded(this.C);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0626Jn
    public final void ZE() {
        this.D.onLoggingImpression(this.C);
    }

    @Override // com.facebook.ads.redexgen.X.K4
    public final void aE() {
        this.D.onMediaDownloaded(this.C);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0626Jn
    public final void yD(KQ error) {
        this.D.onError(this.C, KQ.E(error));
    }
}
