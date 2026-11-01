package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbro implements zzdvi<zzbqc<zzaeh>> {
    private final zzdvv<zzbyd> zzfcs;
    private final zzbrd zzfia;

    public zzbro(zzbrd zzbrdVar, zzdvv<zzbyd> zzdvvVar) {
        this.zzfia = zzbrdVar;
        this.zzfcs = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbqc) zzdvo.zza(new zzbqc(this.zzfcs.get(), zzawx.zzdwb), "Cannot return null from a non-@Nullable @Provides method");
    }
}
