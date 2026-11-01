package com.google.ads.mediation;

import com.google.android.gms.ads.reward.AdMetadataListener;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
final class zza extends AdMetadataListener {
    private final /* synthetic */ AbstractAdViewAdapter zzmu;

    zza(AbstractAdViewAdapter abstractAdViewAdapter) {
        this.zzmu = abstractAdViewAdapter;
    }

    @Override // com.google.android.gms.ads.reward.AdMetadataListener
    public final void onAdMetadataChanged() {
        if (this.zzmu.zzmr == null || this.zzmu.zzms == null) {
            return;
        }
        this.zzmu.zzms.zzb(this.zzmu.zzmr.getAdMetadata());
    }
}
