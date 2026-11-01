package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbex implements zzbrx {
    private zzbox zzelb;
    private zzblu zzelc;
    private final /* synthetic */ zzbeg zzele;
    private zzcko zzeuw;

    private zzbex(zzbeg zzbegVar) {
        this.zzele = zzbegVar;
    }

    @Override // com.google.android.gms.internal.ads.zzblr
    /* JADX INFO: renamed from: zzadc, reason: merged with bridge method [inline-methods] */
    public final zzbry zzabw() {
        zzdvo.zza(this.zzelb, (Class<zzbox>) zzbox.class);
        zzdvo.zza(this.zzelc, (Class<zzblu>) zzblu.class);
        zzdvo.zza(this.zzeuw, (Class<zzcko>) zzcko.class);
        return new zzbew(this.zzele, new zzbks(), new zzcvy(), new zzbll(), new zzcbd(), this.zzelb, this.zzelc, new zzcwg(), this.zzeuw);
    }

    @Override // com.google.android.gms.internal.ads.zzbrx
    public final /* synthetic */ zzbrx zzb(zzcko zzckoVar) {
        this.zzeuw = (zzcko) zzdvo.checkNotNull(zzckoVar);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzbrx
    public final /* synthetic */ zzbrx zzd(zzblu zzbluVar) {
        this.zzelc = (zzblu) zzdvo.checkNotNull(zzbluVar);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzbrx
    public final /* synthetic */ zzbrx zzd(zzbox zzboxVar) {
        this.zzelb = (zzbox) zzdvo.checkNotNull(zzboxVar);
        return this;
    }
}
