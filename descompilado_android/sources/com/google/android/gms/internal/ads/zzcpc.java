package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcpc implements zzdvi<zzcpa> {
    private final zzdvv<zzdcs> zzfbt;

    private zzcpc(zzdvv<zzdcs> zzdvvVar) {
        this.zzfbt = zzdvvVar;
    }

    public static zzcpc zzaj(zzdvv<zzdcs> zzdvvVar) {
        return new zzcpc(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcpa(this.zzfbt.get());
    }
}
