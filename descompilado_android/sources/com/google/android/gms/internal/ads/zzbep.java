package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbep implements zzbhn {
    private zzbox zzelb;
    private zzblu zzelc;
    private final /* synthetic */ zzbeg zzele;

    private zzbep(zzbeg zzbegVar) {
        this.zzele = zzbegVar;
    }

    @Override // com.google.android.gms.internal.ads.zzblr
    /* JADX INFO: renamed from: zzacq, reason: merged with bridge method [inline-methods] */
    public final zzbho zzabw() {
        zzdvo.zza(this.zzelb, (Class<zzbox>) zzbox.class);
        zzdvo.zza(this.zzelc, (Class<zzblu>) zzblu.class);
        return new zzbeo(this.zzele, new zzbks(), new zzcvy(), new zzbll(), new zzcbd(), this.zzelb, this.zzelc, new zzcwg());
    }

    @Override // com.google.android.gms.internal.ads.zzbhn
    @Deprecated
    public final /* synthetic */ zzbhn zza(zzbht zzbhtVar) {
        zzdvo.checkNotNull(zzbhtVar);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzbhn
    public final /* synthetic */ zzbhn zzb(zzblu zzbluVar) {
        this.zzelc = (zzblu) zzdvo.checkNotNull(zzbluVar);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzbhn
    public final /* synthetic */ zzbhn zzb(zzbox zzboxVar) {
        this.zzelb = (zzbox) zzdvo.checkNotNull(zzboxVar);
        return this;
    }
}
