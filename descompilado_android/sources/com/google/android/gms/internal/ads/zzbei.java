package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbei implements zzbsu {
    private zzbox zzelb;
    private zzblu zzelc;
    private zzbsn zzeld;
    private final /* synthetic */ zzbeg zzele;

    private zzbei(zzbeg zzbegVar) {
        this.zzele = zzbegVar;
    }

    @Override // com.google.android.gms.internal.ads.zzblr
    /* JADX INFO: renamed from: zzabv, reason: merged with bridge method [inline-methods] */
    public final zzbsr zzabw() {
        zzdvo.zza(this.zzelb, (Class<zzbox>) zzbox.class);
        zzdvo.zza(this.zzelc, (Class<zzblu>) zzblu.class);
        zzdvo.zza(this.zzeld, (Class<zzbsn>) zzbsn.class);
        return new zzbel(this.zzele, this.zzeld, new zzbks(), new zzcvy(), new zzbll(), new zzcbd(), this.zzelb, this.zzelc, new zzcwg());
    }

    @Override // com.google.android.gms.internal.ads.zzbsu
    public final /* synthetic */ zzbsu zza(zzbsn zzbsnVar) {
        this.zzeld = (zzbsn) zzdvo.checkNotNull(zzbsnVar);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzbsu
    public final /* synthetic */ zzbsu zza(zzblu zzbluVar) {
        this.zzelc = (zzblu) zzdvo.checkNotNull(zzbluVar);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzbsu
    public final /* synthetic */ zzbsu zza(zzbox zzboxVar) {
        this.zzelb = (zzbox) zzdvo.checkNotNull(zzboxVar);
        return this;
    }
}
