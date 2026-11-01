package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbkw implements zzdvi<zzbqc<zzbnm>> {
    private final zzbks zzfft;
    private final zzdvv<zzbkx> zzffu;

    private zzbkw(zzbks zzbksVar, zzdvv<zzbkx> zzdvvVar) {
        this.zzfft = zzbksVar;
        this.zzffu = zzdvvVar;
    }

    public static zzbkw zzd(zzbks zzbksVar, zzdvv<zzbkx> zzdvvVar) {
        return new zzbkw(zzbksVar, zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbqc) zzdvo.zza(new zzbqc(this.zzffu.get(), zzawx.zzdwb), "Cannot return null from a non-@Nullable @Provides method");
    }
}
