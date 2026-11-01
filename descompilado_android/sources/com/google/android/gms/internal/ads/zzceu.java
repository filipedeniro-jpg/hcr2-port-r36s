package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzceu implements zzdvi<zzcep> {
    private final zzdvv<zzcen> zzerh;
    private final zzdvv<zzdcs> zzfvp;

    private zzceu(zzdvv<zzcen> zzdvvVar, zzdvv<zzdcs> zzdvvVar2) {
        this.zzerh = zzdvvVar;
        this.zzfvp = zzdvvVar2;
    }

    public static zzceu zzai(zzdvv<zzcen> zzdvvVar, zzdvv<zzdcs> zzdvvVar2) {
        return new zzceu(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcep(this.zzerh.get(), this.zzfvp.get());
    }
}
