package com.facebook.ads.redexgen.X;

import com.facebook.ads.NativeAd;
import com.facebook.ads.internal.api.MediaViewVideoRendererApi;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Js, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0631Js implements InterfaceC0629Jq {
    public final /* synthetic */ C0638Jz B;
    public final /* synthetic */ MediaViewVideoRendererApi C;

    public C0631Js(C0638Jz this$0, MediaViewVideoRendererApi mediaViewVideoRendererApi) {
        this.B = this$0;
        this.C = mediaViewVideoRendererApi;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0629Jq
    public final void pF() {
        this.C.setVolume(0.0f);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0629Jq
    public final void qF(NativeAd nativeAd) {
        this.B.W((KF) nativeAd.getInternalNativeAd(), null);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0629Jq
    public final void yF() {
        this.B.T();
    }
}
