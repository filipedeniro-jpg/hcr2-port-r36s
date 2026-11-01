package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcik implements zzdvi<zzcih> {
    private final zzdvv<zzbds> zzfiv;
    private final zzdvv<zzblu.zza> zzfiw;
    private final zzdvv<zzbox> zzfix;
    private final zzdvv<zzcko> zzfyp;

    public zzcik(zzdvv<zzbds> zzdvvVar, zzdvv<zzblu.zza> zzdvvVar2, zzdvv<zzcko> zzdvvVar3, zzdvv<zzbox> zzdvvVar4) {
        this.zzfiv = zzdvvVar;
        this.zzfiw = zzdvvVar2;
        this.zzfyp = zzdvvVar3;
        this.zzfix = zzdvvVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcih(this.zzfiv.get(), this.zzfiw.get(), this.zzfyp.get(), this.zzfix.get());
    }
}
