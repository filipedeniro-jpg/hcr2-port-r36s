package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzblk implements zzdvi<zzbqc<com.google.android.gms.ads.internal.overlay.zzp>> {
    private final zzdvv<zzbko> zzfcs;
    private final zzblh zzfga;

    private zzblk(zzblh zzblhVar, zzdvv<zzbko> zzdvvVar) {
        this.zzfga = zzblhVar;
        this.zzfcs = zzdvvVar;
    }

    public static zzblk zza(zzblh zzblhVar, zzdvv<zzbko> zzdvvVar) {
        return new zzblk(zzblhVar, zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbqc) zzdvo.zza(new zzbqc(this.zzfcs.get(), zzawx.zzdwb), "Cannot return null from a non-@Nullable @Provides method");
    }
}
