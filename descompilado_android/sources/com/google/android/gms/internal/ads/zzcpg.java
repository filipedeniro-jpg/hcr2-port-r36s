package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcpg implements zzdvi<zzcpe> {
    private final zzdvv<zzdcs> zzfbt;

    private zzcpg(zzdvv<zzdcs> zzdvvVar) {
        this.zzfbt = zzdvvVar;
    }

    public static zzcpg zzak(zzdvv<zzdcs> zzdvvVar) {
        return new zzcpg(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcpe(this.zzfbt.get());
    }
}
