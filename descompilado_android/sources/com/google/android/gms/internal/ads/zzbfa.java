package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbfa implements zzbyl {
    private zzbox zzelb;
    private zzblu zzelc;
    private final /* synthetic */ zzbeg zzele;

    private zzbfa(zzbeg zzbegVar) {
        this.zzele = zzbegVar;
    }

    @Override // com.google.android.gms.internal.ads.zzblr
    /* JADX INFO: renamed from: zzadh, reason: merged with bridge method [inline-methods] */
    public final zzbym zzabw() {
        zzdvo.zza(this.zzelb, (Class<zzbox>) zzbox.class);
        zzdvo.zza(this.zzelc, (Class<zzblu>) zzblu.class);
        return new zzbfd(this.zzele, new zzbks(), new zzcvy(), new zzbll(), new zzcbd(), this.zzelb, this.zzelc, new zzcwg());
    }

    @Override // com.google.android.gms.internal.ads.zzbyl
    public final /* synthetic */ zzbyl zze(zzblu zzbluVar) {
        this.zzelc = (zzblu) zzdvo.checkNotNull(zzbluVar);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzbyl
    public final /* synthetic */ zzbyl zze(zzbox zzboxVar) {
        this.zzelb = (zzbox) zzdvo.checkNotNull(zzboxVar);
        return this;
    }
}
