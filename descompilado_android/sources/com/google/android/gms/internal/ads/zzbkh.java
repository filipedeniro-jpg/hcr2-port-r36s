package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbkh implements zzdcg<zzbju> {
    private final /* synthetic */ zzdcg zzfff;
    private final /* synthetic */ zzbkb zzffg;

    zzbkh(zzbkb zzbkbVar, zzdcg zzdcgVar) {
        this.zzffg = zzbkbVar;
        this.zzfff = zzdcgVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
        this.zzffg.zzafj();
        this.zzfff.zzb(th);
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final /* synthetic */ void onSuccess(zzbju zzbjuVar) {
        this.zzffg.zzafj();
        this.zzfff.onSuccess(zzbjuVar);
    }
}
