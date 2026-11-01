package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbma implements zzdvi<zzblu.zza> {
    private final zzblu zzfgi;

    private zzbma(zzblu zzbluVar) {
        this.zzfgi = zzbluVar;
    }

    public static zzbma zzj(zzblu zzbluVar) {
        return new zzbma(zzbluVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzblu.zza) zzdvo.zza(this.zzfgi.zzafp(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
