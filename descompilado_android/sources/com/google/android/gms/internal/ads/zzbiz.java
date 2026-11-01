package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbiz implements zzdvi<zzcfk<zzbhy>> {
    private final zzdvv<zzdcs> zzfdl;
    private final zzdvv<zzcxn> zzfdw;
    private final zzdvv<zzcgg> zzfdx;
    private final zzdvv<zzcju> zzfdy;

    public zzbiz(zzdvv<zzcxn> zzdvvVar, zzdvv<zzdcs> zzdvvVar2, zzdvv<zzcgg> zzdvvVar3, zzdvv<zzcju> zzdvvVar4) {
        this.zzfdw = zzdvvVar;
        this.zzfdl = zzdvvVar2;
        this.zzfdx = zzdvvVar3;
        this.zzfdy = zzdvvVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzcfk) zzdvo.zza(new zzcjw(this.zzfdw.get(), this.zzfdl.get(), this.zzfdy.get(), this.zzfdx.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
