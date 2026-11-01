package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbeq implements zzbiw {
    private zzbox zzelb;
    private zzblu zzelc;
    private zzbsn zzeld;
    private final /* synthetic */ zzbeg zzele;
    private zzcko zzeuw;
    private zzbjn zzeux;
    private zzbht zzeuy;

    private zzbeq(zzbeg zzbegVar) {
        this.zzele = zzbegVar;
    }

    @Override // com.google.android.gms.internal.ads.zzblr
    /* JADX INFO: renamed from: zzacr, reason: merged with bridge method [inline-methods] */
    public final zzbit zzabw() {
        zzdvo.zza(this.zzelb, (Class<zzbox>) zzbox.class);
        zzdvo.zza(this.zzelc, (Class<zzblu>) zzblu.class);
        zzdvo.zza(this.zzeuw, (Class<zzcko>) zzcko.class);
        zzdvo.zza(this.zzeux, (Class<zzbjn>) zzbjn.class);
        zzdvo.zza(this.zzeuy, (Class<zzbht>) zzbht.class);
        zzdvo.zza(this.zzeld, (Class<zzbsn>) zzbsn.class);
        return new zzbet(this.zzele, this.zzeuy, this.zzeld, new zzbks(), new zzcvy(), new zzbll(), new zzcbd(), this.zzelb, this.zzelc, new zzcwg(), this.zzeuw, this.zzeux);
    }

    @Override // com.google.android.gms.internal.ads.zzbiw
    public final /* synthetic */ zzbiw zzb(zzbsn zzbsnVar) {
        this.zzeld = (zzbsn) zzdvo.checkNotNull(zzbsnVar);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzbiw
    public final /* synthetic */ zzbiw zzb(zzbht zzbhtVar) {
        this.zzeuy = (zzbht) zzdvo.checkNotNull(zzbhtVar);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzbiw
    public final /* synthetic */ zzbiw zza(zzbjn zzbjnVar) {
        this.zzeux = (zzbjn) zzdvo.checkNotNull(zzbjnVar);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzbiw
    public final /* synthetic */ zzbiw zza(zzcko zzckoVar) {
        this.zzeuw = (zzcko) zzdvo.checkNotNull(zzckoVar);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzbiw
    public final /* synthetic */ zzbiw zzc(zzblu zzbluVar) {
        this.zzelc = (zzblu) zzdvo.checkNotNull(zzbluVar);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzbiw
    public final /* synthetic */ zzbiw zzc(zzbox zzboxVar) {
        this.zzelb = (zzbox) zzdvo.checkNotNull(zzboxVar);
        return this;
    }
}
