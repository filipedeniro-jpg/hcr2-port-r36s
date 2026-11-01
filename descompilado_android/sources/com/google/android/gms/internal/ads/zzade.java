package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.UnifiedNativeAd;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzade extends zzaco {
    private final UnifiedNativeAd.UnconfirmedClickListener zzcwq;

    public zzade(UnifiedNativeAd.UnconfirmedClickListener unconfirmedClickListener) {
        this.zzcwq = unconfirmedClickListener;
    }

    @Override // com.google.android.gms.internal.ads.zzacl
    public final void onUnconfirmedClickReceived(String str) {
        this.zzcwq.onUnconfirmedClickReceived(str);
    }

    @Override // com.google.android.gms.internal.ads.zzacl
    public final void onUnconfirmedClickCancelled() {
        this.zzcwq.onUnconfirmedClickCancelled();
    }
}
