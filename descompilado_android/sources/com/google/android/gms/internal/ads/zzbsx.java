package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbsx implements zzdvi<zzbjv<zzbhy>> {
    private final zzdvv<zzboa> zzfge;
    private final zzdvv<zzbds> zzfiv;
    private final zzdvv<zzblu.zza> zzfiw;
    private final zzdvv<zzbox> zzfix;
    private final zzdvv<zzbsn> zzfiy;

    public zzbsx(zzdvv<zzbds> zzdvvVar, zzdvv<zzblu.zza> zzdvvVar2, zzdvv<zzbox> zzdvvVar3, zzdvv<zzbsn> zzdvvVar4, zzdvv<zzboa> zzdvvVar5) {
        this.zzfiv = zzdvvVar;
        this.zzfiw = zzdvvVar2;
        this.zzfix = zzdvvVar3;
        this.zzfiy = zzdvvVar4;
        this.zzfge = zzdvvVar5;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        zzbds zzbdsVar = this.zzfiv.get();
        zzblu.zza zzaVar = this.zzfiw.get();
        zzbox zzboxVar = this.zzfix.get();
        return (zzbjv) zzdvo.zza(zzbdsVar.zzabi().zzc(zzaVar.zzafu()).zzc(zzboxVar).zzb(this.zzfiy.get()).zza(new zzcko(null)).zza(new zzbjn(this.zzfge.get())).zzb(new zzbht(null)).zzacr().zzacz(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
