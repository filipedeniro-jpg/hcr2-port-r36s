package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbkr implements zzdvi<zzbqc<zzth>> {
    private final zzbks zzfft;
    private final zzdvv<zzbkx> zzffu;

    private zzbkr(zzbks zzbksVar, zzdvv<zzbkx> zzdvvVar) {
        this.zzfft = zzbksVar;
        this.zzffu = zzdvvVar;
    }

    public static zzbkr zza(zzbks zzbksVar, zzdvv<zzbkx> zzdvvVar) {
        return new zzbkr(zzbksVar, zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbqc) zzdvo.zza(new zzbqc(this.zzffu.get(), zzawx.zzdwb), "Cannot return null from a non-@Nullable @Provides method");
    }
}
