package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcnr implements zzdvi<zzcnp> {
    private final zzdvv<zzdcs> zzfbt;
    private final zzdvv<zzcvk> zzfds;

    private zzcnr(zzdvv<zzdcs> zzdvvVar, zzdvv<zzcvk> zzdvvVar2) {
        this.zzfbt = zzdvvVar;
        this.zzfds = zzdvvVar2;
    }

    public static zzcnr zzak(zzdvv<zzdcs> zzdvvVar, zzdvv<zzcvk> zzdvvVar2) {
        return new zzcnr(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcnp(this.zzfbt.get(), this.zzfds.get());
    }
}
