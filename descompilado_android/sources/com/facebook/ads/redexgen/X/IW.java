package com.facebook.ads.redexgen.X;

import com.facebook.ads.AudienceNetworkAds;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class IW implements Runnable {
    public final /* synthetic */ AudienceNetworkAds.InitResult B;
    public final /* synthetic */ AudienceNetworkAds.InitListener C;

    public IW(AudienceNetworkAds.InitListener initListener, AudienceNetworkAds.InitResult initResult) {
        this.C = initListener;
        this.B = initResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C0588Ia.L(this.C, this.B);
    }
}
