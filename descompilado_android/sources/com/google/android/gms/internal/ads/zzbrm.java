package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbrm implements zzdvi<zzbqc<zzbnm>> {
    private final zzdvv<zzbsd> zzfcs;
    private final zzbrd zzfia;

    private zzbrm(zzbrd zzbrdVar, zzdvv<zzbsd> zzdvvVar) {
        this.zzfia = zzbrdVar;
        this.zzfcs = zzdvvVar;
    }

    public static zzbrm zzc(zzbrd zzbrdVar, zzdvv<zzbsd> zzdvvVar) {
        return new zzbrm(zzbrdVar, zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbqc) zzdvo.zza(new zzbqc(this.zzfcs.get(), zzawx.zzdwb), "Cannot return null from a non-@Nullable @Provides method");
    }
}
