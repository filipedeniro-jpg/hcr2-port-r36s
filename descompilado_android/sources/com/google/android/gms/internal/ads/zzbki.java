package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbki implements zzdcg<zzbka> {
    private final /* synthetic */ zzdcg zzfff;
    private final /* synthetic */ zzbkb zzffg;

    zzbki(zzbkb zzbkbVar, zzdcg zzdcgVar) {
        this.zzffg = zzbkbVar;
        this.zzfff = zzdcgVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
        this.zzfff.zzb(th);
        this.zzffg.zzafj();
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final /* synthetic */ void onSuccess(zzbka zzbkaVar) {
        this.zzffg.zza(zzbkaVar.zzfey, this.zzfff);
    }
}
