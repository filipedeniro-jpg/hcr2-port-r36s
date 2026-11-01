package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzblz implements zzdvi<String> {
    private final zzdvv<zzbkx> zzeog;
    private final zzblu zzfgi;

    private zzblz(zzblu zzbluVar, zzdvv<zzbkx> zzdvvVar) {
        this.zzfgi = zzbluVar;
        this.zzeog = zzdvvVar;
    }

    public static zzblz zzb(zzblu zzbluVar, zzdvv<zzbkx> zzdvvVar) {
        return new zzblz(zzbluVar, zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (String) zzdvo.zza(this.zzeog.get().zztt(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
