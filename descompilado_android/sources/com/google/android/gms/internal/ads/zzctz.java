package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.reward.AdMetadataListener;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzctz implements zzcug {
    static final zzcug zzghn = new zzctz();

    private zzctz() {
    }

    @Override // com.google.android.gms.internal.ads.zzcug
    public final void zzt(Object obj) {
        ((AdMetadataListener) obj).onAdMetadataChanged();
    }
}
