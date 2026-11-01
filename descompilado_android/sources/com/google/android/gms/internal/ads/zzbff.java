package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbff implements zzcmi {
    private zzblu zzelc;
    private final /* synthetic */ zzbeg zzele;
    private zzcmm zzezr;

    private zzbff(zzbeg zzbegVar) {
        this.zzele = zzbegVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcmi
    public final zzcmf zzado() {
        zzdvo.zza(this.zzelc, (Class<zzblu>) zzblu.class);
        zzdvo.zza(this.zzezr, (Class<zzcmm>) zzcmm.class);
        return new zzbfe(this.zzele, this.zzezr, new zzbks(), new zzcbd(), this.zzelc, new zzcwg());
    }

    @Override // com.google.android.gms.internal.ads.zzcmi
    @Deprecated
    public final /* synthetic */ zzcmi zzf(zzbox zzboxVar) {
        zzdvo.checkNotNull(zzboxVar);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzcmi
    public final /* synthetic */ zzcmi zza(zzcmm zzcmmVar) {
        this.zzezr = (zzcmm) zzdvo.checkNotNull(zzcmmVar);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzcmi
    public final /* synthetic */ zzcmi zzf(zzblu zzbluVar) {
        this.zzelc = (zzblu) zzdvo.checkNotNull(zzbluVar);
        return this;
    }
}
