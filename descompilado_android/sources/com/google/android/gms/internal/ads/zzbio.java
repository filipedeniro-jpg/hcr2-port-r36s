package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbio implements zzdvi<zzbqc<zzbnm>> {
    private final zzdvv<zzbjj> zzfcs;
    private final zzbib zzfdq;

    public zzbio(zzbib zzbibVar, zzdvv<zzbjj> zzdvvVar) {
        this.zzfdq = zzbibVar;
        this.zzfcs = zzdvvVar;
    }

    public static zzbqc<zzbnm> zza(zzbib zzbibVar, zzbjj zzbjjVar) {
        return (zzbqc) zzdvo.zza(new zzbqc(zzbjjVar, zzawx.zzdwa), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zza(this.zzfdq, this.zzfcs.get());
    }
}
