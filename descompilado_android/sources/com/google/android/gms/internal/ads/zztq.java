package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.reward.AdMetadataListener;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zztq extends zzvk {
    private final AdMetadataListener zzccb;

    public zztq(AdMetadataListener adMetadataListener) {
        this.zzccb = adMetadataListener;
    }

    @Override // com.google.android.gms.internal.ads.zzvh
    public final void onAdMetadataChanged() {
        AdMetadataListener adMetadataListener = this.zzccb;
        if (adMetadataListener != null) {
            adMetadataListener.onAdMetadataChanged();
        }
    }
}
