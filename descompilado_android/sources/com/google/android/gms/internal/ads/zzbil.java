package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbil implements zzdvi<zzbqc<zzbmt>> {
    private final zzdvv<zzbjj> zzfcs;
    private final zzbib zzfdq;

    public zzbil(zzbib zzbibVar, zzdvv<zzbjj> zzdvvVar) {
        this.zzfdq = zzbibVar;
        this.zzfcs = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbqc) zzdvo.zza(new zzbqc(this.zzfcs.get(), zzawx.zzdwa), "Cannot return null from a non-@Nullable @Provides method");
    }
}
